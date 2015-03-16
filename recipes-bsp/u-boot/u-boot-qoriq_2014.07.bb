DESCRIPTION = "U-boot bootloader"
HOMEPAGE = "http://u-boot.sf.net"
SECTION = "bootloaders"
PROVIDES = "virtual/bootloader u-boot"
LICENSE = "GPLv2 & BSD-3-Clause & BSD-2-Clause & LGPL-2.0 & LGPL-2.1"
LIC_FILES_CHKSUM = " \
    file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://Licenses/bsd-2-clause.txt;md5=6a31f076f5773aabd8ff86191ad6fdd5 \
    file://Licenses/bsd-3-clause.txt;md5=4a1190eac56a9db675d58ebe86eaf50c \
    file://Licenses/lgpl-2.0.txt;md5=5f30f0716dfdd0d91eb439ebec522ec2 \
    file://Licenses/lgpl-2.1.txt;md5=4fbd65380cdd255951079008b364516c \
"

PV = "2014.07+fslgit"
INHIBIT_DEFAULT_DEPS = "1"
DEPENDS = "boot-format-native libgcc ${@base_contains('TCMODE', 'external-fsl', '', 'virtual/${TARGET_PREFIX}gcc', d)}"

inherit deploy

SRC_URI = "git://git.freescale.com/ppc/sdk/u-boot.git;nobranch=1"
SRCREV = "659b6a23a8b1f3026200bc6352dbacef53f4dcb1"

python () {
    if d.getVar("TCMODE", True) == "external-fsl":
        return

    ml = d.getVar("MULTILIB_VARIANTS", True)
    arch = d.getVar("OVERRIDES", True)

    if ("e5500-64b:" in arch or "e6500-64b:" in arch) and not "lib32" in ml:
        raise bb.parse.SkipPackage("Building the u-boot for this arch requires multilib to be enabled")
}

DEPENDS_append_e5500-64b = "${@base_contains('TCMODE', 'external-fsl', '', ' lib32-gcc-cross lib32-libgcc', d)}"
PATH_append_e5500-64b = ":${STAGING_BINDIR_NATIVE}/${DEFAULTTUNE_virtclass-multilib-lib32}${TARGET_VENDOR_virtclass-multilib-lib32}-${HOST_OS}/"
TOOLCHAIN_OPTIONS_append_e5500-64b = "${@base_contains('TCMODE', 'external-fsl', '', '/../lib32-${MACHINE}', d)}"
TARGET_VENDOR_virtclass-multilib-lib32 ?= "${@base_contains('TCMODE', 'external-fsl', '', '-${DISTRO}mllib32', d)}"
WRAP_TARGET_PREFIX_e5500-64b := "powerpc${TARGET_VENDOR_virtclass-multilib-lib32}-${HOST_OS}-"

DEPENDS_append_e6500-64b = "${@base_contains('TCMODE', 'external-fsl', '', ' lib32-gcc-cross lib32-libgcc', d)}"
PATH_append_e6500-64b = ":${STAGING_BINDIR_NATIVE}/${DEFAULTTUNE_virtclass-multilib-lib32}${TARGET_VENDOR_virtclass-multilib-lib32}-${HOST_OS}/"
TOOLCHAIN_OPTIONS_append_e6500-64b = "${@base_contains('TCMODE', 'external-fsl', '', '/../lib32-${MACHINE}', d)}"
TARGET_VENDOR_virtclass-multilib-lib32 ?= "${@base_contains('TCMODE', 'external-fsl', '', '-${DISTRO}mllib32', d)}"
WRAP_TARGET_PREFIX_e6500-64b := "powerpc${TARGET_VENDOR_virtclass-multilib-lib32}-${HOST_OS}-"
WRAP_TARGET_PREFIX = "${TARGET_PREFIX}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

UBOOT_LOCALVERSION = "${@d.getVar('SDK_VERSION', True).partition(' ')[0]}"

USRC ?= ""
S = '${@base_conditional("USRC", "", "${WORKDIR}/git", "${USRC}", d)}'

CROSS_COMPILE = '${@base_conditional("TCMODE", "external-fsl", "${TARGET_PREFIX}", "${WRAP_TARGET_PREFIX}", d)}'
EXTRA_OEMAKE = 'CROSS_COMPILE=${CROSS_COMPILE} CC="${CROSS_COMPILE}gcc ${TOOLCHAIN_OPTIONS}"'

do_compile () {
    unset LDFLAGS
    unset CFLAGS
    unset CPPFLAGS

    if [ ! -e ${B}/.scmversion -a ! -e ${S}/.scmversion ]
    then
        head=`git rev-parse --verify --short HEAD 2> /dev/null`
        printf "%s%s%s" ${UBOOT_LOCALVERSION} +g $head > ${B}/.scmversion
        printf "%s%s%s" ${UBOOT_LOCALVERSION} +g $head > ${S}/.scmversion
    fi

    if [ "x${UBOOT_MACHINES}" = "x" ]; then
        UBOOT_MACHINES=${UBOOT_MACHINE}
    fi

    python ./tools/genboardscfg.py
    for board in ${UBOOT_MACHINES}; do
        if ! grep -wq $board ${S}/boards.cfg;then
            echo "WARNING: $board not supported in boards.cfg"
            continue
        fi

        oe_runmake O=${board} distclean
        oe_runmake O=${board} ${board}_config
        oe_runmake O=${board} all

        case "${board}" in
            *SDCARD*)   UBOOT_TARGET="u-boot-sd";;
            *SPIFLASH*) UBOOT_TARGET="u-boot-spi";;
            *NAND*)     UBOOT_TARGET="u-boot-nand";;
            *SRIO*)     UBOOT_TARGET="u-boot-srio";;
            *)      UBOOT_TARGET="";;
        esac

        # deal with sd/spi/nand/srio image
        UBOOT_SOURCE=u-boot.bin
        if [ "x${UBOOT_TARGET}" != "x" ] && echo $board |egrep -qi "SECBOOT|SECURE"; then
            cp ${S}/${board}/${UBOOT_SOURCE}  ${S}/${board}/${UBOOT_TARGET}.bin
        elif [ "x${UBOOT_TARGET}" != "x" ]; then
            # some boards' final binary was not named as u-boot.bin
            if [ "${UBOOT_TARGET}" = "u-boot-nand" ];then
                if echo $board |egrep -q "^(BSC|C29|P10|P2020RDB)";then
                    UBOOT_SOURCE=u-boot-with-spl.bin
                elif echo $board |egrep -q "^(B4|T1|T2|T4)";then
                    UBOOT_SOURCE=u-boot-with-spl-pbl.bin
                elif echo $board |egrep -q "^(P2041|P3|P4|P5)";then
                    UBOOT_SOURCE=u-boot.pbl
                fi
            elif [ "${UBOOT_TARGET}" = "u-boot-spi" ];then
                if echo $board |egrep -q "^(P10|P2020RDB)";then
                    UBOOT_SOURCE=u-boot-with-spl.bin
                elif echo $board |egrep -q "^(T1|T2)";then
                    UBOOT_SOURCE=u-boot-with-spl-pbl.bin
                elif echo $board |egrep -q "^(B4|P2041|P3|P4|P5|T4)";then
                    UBOOT_SOURCE=u-boot.pbl
                fi
            elif [ "${UBOOT_TARGET}" = "u-boot-sd" ];then
                if echo $board |egrep -q "^(P10|P2020RDB)";then
                    UBOOT_SOURCE=u-boot-with-spl.bin
                elif echo $board |egrep -q "^(B4|T1|T2|T4)";then
                    UBOOT_SOURCE=u-boot-with-spl-pbl.bin
                elif echo $board |egrep -q "^(P2041|P3|P4|P5)";then
                    UBOOT_SOURCE=u-boot.pbl
                fi
            fi
            cp ${S}/${board}/${UBOOT_SOURCE}  ${S}/${board}/${UBOOT_TARGET}.bin

            # use boot-format to regenerate spi image if BOOTFORMAT_CONFIG is not empty
            if [ "${UBOOT_TARGET}" = "u-boot-spi" ] && [ -n "${BOOTFORMAT_CONFIG}" ];then
                ${STAGING_BINDIR_NATIVE}/boot_format \
                ${STAGING_DATADIR_NATIVE}/boot_format/${BOOTFORMAT_CONFIG} \
                ${S}/${board}/${UBOOT_SOURCE} -spi ${S}/${board}/${UBOOT_TARGET}.bin
            fi
        fi
    done
}

do_install(){
    if [ "x${UBOOT_MACHINES}" = "x" ]; then
        UBOOT_MACHINES=${UBOOT_MACHINE}
    fi

    for board in ${UBOOT_MACHINES}; do
        if ! grep -wq $board ${S}/boards.cfg;then
            continue
        fi

        case "${board}" in
            *SDCARD*)   UBOOT_TARGET="u-boot-sd";;
            *SPIFLASH*) UBOOT_TARGET="u-boot-spi";;
            *NAND*)     UBOOT_TARGET="u-boot-nand";;
            *SRIO*)     UBOOT_TARGET="u-boot-srio";;
            *)      UBOOT_TARGET="u-boot";;
        esac

        if [ -f ${S}/${board}/${UBOOT_TARGET}.bin ]; then
            mkdir -p ${D}/boot/
            install ${S}/${board}/${UBOOT_TARGET}.bin ${D}/boot/${UBOOT_TARGET}-${board}-${PV}-${PR}.bin
            ln -sf ${UBOOT_TARGET}-${board}-${PV}-${PR}.bin ${D}/boot/${UBOOT_TARGET}.bin
        fi
    done
}

do_deploy(){
    if [ "x${UBOOT_MACHINES}" = "x" ]; then
        UBOOT_MACHINES=${UBOOT_MACHINE}
    fi

    for board in ${UBOOT_MACHINES}; do
        if ! grep -wq $board ${S}/boards.cfg;then
            continue
        fi

        case "${board}" in
            *SDCARD*)   UBOOT_TARGET="u-boot-sd";;
            *SPIFLASH*) UBOOT_TARGET="u-boot-spi";;
            *NAND*)     UBOOT_TARGET="u-boot-nand";;
            *SRIO*)     UBOOT_TARGET="u-boot-srio";;
            *)      UBOOT_TARGET="u-boot";;
        esac

        if [ -f ${S}/${board}/${UBOOT_TARGET}.bin ]; then
            mkdir -p ${DEPLOYDIR}
            install ${S}/${board}/${UBOOT_TARGET}.bin ${DEPLOYDIR}/${UBOOT_TARGET}-${board}-${PV}-${PR}.bin

            cd ${DEPLOYDIR}
            rm -f ${UBOOT_TARGET}-${board}.bin
            ln -sf ${UBOOT_TARGET}-${board}-${PV}-${PR}.bin ${UBOOT_TARGET}-${board}.bin
        fi
    done
}
addtask deploy after do_install

PACKAGES += "${PN}-images"
FILES_${PN}-images += "/boot"

ALLOW_EMPTY_${PN} = "1"
