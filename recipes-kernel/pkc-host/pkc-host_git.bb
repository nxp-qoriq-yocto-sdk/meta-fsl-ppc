DESCRIPTION = "pkc host driver"
SECTION = "pkc-host"
LICENSE = "BSD & GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=99803d8e9a595c0bdb45ca710f353813"

inherit  module qoriq_build_64bit_kernel
RDEPENDS_${PN} += "cryptodev-module bc"

# NOTE: remove this requirement and all traces of DISTRO_FEATURE c29x_pkc
# if pkc-host does not need customized cryptodev patches anymore
REQUIRED_DISTRO_FEATURES = "c29x_pkc"

SRC_URI = "git://git.freescale.com/ppc/sdk/pkc-host.git;nobranch=1"
SRCREV = "31a80a6134e69541eaa0b4ec58c9e63d00680c75"

S = "${WORKDIR}/git"

EXTRA_OEMAKE='KERNEL_DIR="${STAGING_KERNEL_DIR}" PREFIX="${D}"'

do_install() {
        oe_runmake INSTALL_MOD_PATH="${D}" modules_install
        install -d ${D}/etc/crypto
        install -d ${D}/${bindir}
        cp ${S}/crypto.cfg ${D}/etc/crypto
        cp ${S}/images/pkc-firmware.bin ${D}/etc/crypto
        cp ${S}/apps/cli/cli ${D}/${bindir}
        cp ${S}/perf/c29x_driver_perf_profile.sh ${D}/${bindir}
}

FILES_${PN} = "${bindir}/cli \
        ${bindir}/c29x_driver_perf_profile.sh \
        /etc/crypto/crypto.cfg \
        /etc/crypto/pkc-firmware.bin \
"
