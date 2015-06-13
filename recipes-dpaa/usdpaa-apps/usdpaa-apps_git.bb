DESCRIPTION = "User-Space Data-Path Acceleration Architecture Demo Applications"
LICENSE = "BSD & GPLv2"
LIC_FILES_CHKSUM = "file://Makefile;endline=30;md5=d2a5d894118910d49993347f3f6e0f1e"

inherit pkgconfig

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "libxml2 libedit ncurses readline fmc usdpaa dpa-offload libnl"
DEPENDS_append_b4860qds = " ipc-ust"
DEPENDS_append_b4420qds = " ipc-ust"

RDEPENDS_${PN} = "libgcc bash"

SRC_URI = "git://git.freescale.com/ppc/sdk/usdpaa/usdpaa-apps;nobranch=1"
SRCREV = "95744440cf214d73884c44c0dbc296e1e4b4c8f4"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = 'CC="${CC}" LD="${LD}" AR="${AR}"'
export ARCH="${TARGET_ARCH}"

do_compile_prepend () {
    case ${MACHINE} in
        b4420qds|b4420qds-64b|b4860qds|b4860qds-64b)
            SOC=B4860;
            FMAN_VARIANT=FMAN_V3H;;
        t1040d4rdb|t1040d4rdb-64b|t1042d4rdb|t1042d4rdb-64b|t1024qds|t1024qds-64b|t1024rdb|t1024rdb-64b|t1040qds|t1040qds-64b|t1040rdb|t1040rdb-64b|t1042rdb|t1042rdb-64b|t1042rdb-pi|t1042rdb-pi-64b)
            SOC=T1040;
            FMAN_VARIANT=FMAN_V3L;;
        t2080qds|t2080qds-64b|t2080rdb|t2080rdb-64b)
            SOC=T2080;
            FMAN_VARIANT=FMAN_V3H;;
        t4240qds|t4240qds-64b|t4240rdb|t4240rdb-64b|t4160qds|t4160qds-64b)
            SOC=T4240;
            FMAN_VARIANT=FMAN_V3H;;
        p1023rdb)
            SOC=P1023;
            FMAN_VARIANT=P1023;;
        *)
            SOC=P4080;
            FMAN_VARIANT=P4080;;
    esac
    export SOC=$SOC

    export FMC_EXTRA_CFLAGS="-I ${STAGING_INCDIR}/fmc"
    export FMLIB_EXTRA_CFLAGS="-I ${STAGING_INCDIR}/fmd \
        -I ${STAGING_INCDIR}/fmd/Peripherals \
        -I ${STAGING_INCDIR}/fmd/integrations \
        -D$FMAN_VARIANT"
    export USDPAA_EXTRA_CFLAGS="-I ${STAGING_INCDIR}/usdpaa"
    export DPAOFFLOAD_EXTRA_CFLAGS="-I ${STAGING_INCDIR}/dpa-offload"
    export LIBNL_EXTRA_CFLAGS="-I ${STAGING_INCDIR}/libnl3"
    export LIBNL_EXTRA_LDFLAGS="-lnl-3 -lnl-route-3"
    export LIBXML2_CFLAGS="$(pkg-config --cflags libxml-2.0)"
    export LIBXML2_LDFLAGS="$(pkg-config --libs --static libxml-2.0)"
    export LIBEDIT_CFLAGS="$(pkg-config --cflags libedit)"
    export LIBEDIT_LDFLAGS="$(pkg-config --libs --static libedit)"
}

do_install () {
    case ${MACHINE} in
        b4420qds|b4420qds-64b|b4860qds|b4860qds-64b) SOC=B4860;;
        t1040d4rdb|t1040d4rdb-64b|t1042d4rdb|t1042d4rdb-64b|t1024qds|t1024qds-64b|t1024rdb|t1024rdb-64b|t1040qds|t1040qds-64b|t1040rdb|t1040rdb-64b|t1042rdb|t1042rdb-64b|t1042rdb-pi|t1042rdb-pi-64b) SOC=T1040;;
        t2080qds|t2080qds-64b|t2080rdb|t2080rdb-64b) SOC=T2080;;
        t4240qds|t4240qds-64b|t4240rdb|t4240rdb-64b|t4160qds|t4160qds-64b) SOC=T4240;;
        p1023rdb) SOC=P1023;;
        *) SOC=P4080;;
    esac
    export SOC=$SOC
    oe_runmake install DESTDIR=${D}
}

PARALLEL_MAKE_pn-${PN} = ""
FILES_${PN} += "/root/SOURCE_THIS /usr/etc/"

COMPATIBLE_HOST_qoriq-ppc = ".*"
COMPATIBLE_HOST ?= "(none)"
