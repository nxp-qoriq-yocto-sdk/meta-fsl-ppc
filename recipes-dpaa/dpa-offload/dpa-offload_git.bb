DESCRIPTION = "Data-Path Acceleration Architecture Offloading User-Space Drivers"
LICENSE = "BSD & GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=c03ebb7a330b209986517466c74da72c"

inherit pkgconfig

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "usdpaa fmlib"
DEPENDS_append_b4860qds = " ipc-ust"
DEPENDS_append_b4420qds = " ipc-ust"

RDEPENDS_${PN} = "libgcc bash"

SRC_URI = "git://git.freescale.com/ppc/sdk/usdpaa/dpa-offload.git;nobranch=1"
SRCREV = "ed6191db9439d1aaba27fc01238da770d1d8ef9e"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = 'CC="${CC}" LD="${LD}"'
export ARCH="${TARGET_ARCH}"

PARALLEL_MAKE_pn-${PN} = ""

do_compile_prepend () {
    case ${MACHINE} in
        b4420qds|b4420qds-64b|b4860qds|b4860qds-64b)
            FMAN_VARIANT=FMAN_V3H;;
        t1040d4rdb|t1040d4rdb-64b|t1042d4rdb|t1042d4rdb-64b|t1024qds|t1024qds-64b|t1024rdb|t1024rdb-64b|t1040qds|t1040qds-64b|t1040rdb|t1040rdb-64b|t1042rdb|t1042rdb-64b|t1042rdb-pi|t1042rdb-pi-64b)
            FMAN_VARIANT=FMAN_V3L;;
        t2080qds|t2080qds-64b|t2080rdb|t2080rdb-64b)
            FMAN_VARIANT=FMAN_V3H;;
        t4240qds|t4240qds-64b|t4240rdb|t4240rdb-64b|t4160qds|t4160qds-64b)
            FMAN_VARIANT=FMAN_V3H;;
        p1023rdb)
            FMAN_VARIANT=P1023;;
        *)
            FMAN_VARIANT=P4080;;
    esac

    export USDPAA_CFLAGS="-I ${STAGING_INCDIR}/usdpaa"
    export FMLIB_CFLAGS="-I ${STAGING_INCDIR}/fmd \
        -I ${STAGING_INCDIR}/fmd/Peripherals \
        -I ${STAGING_INCDIR}/fmd/integrations \
        -D$FMAN_VARIANT -DNCSW_LINUX"
}

do_install () {
    oe_runmake install DESTDIR=${D}
}

ALLOW_EMPTY_${PN} = "1"

COMPATIBLE_HOST_qoriq-ppc = ".*"
COMPATIBLE_HOST ?= "(none)"
