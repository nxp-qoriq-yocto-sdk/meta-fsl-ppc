DESCRIPTION = "Foundation Library"
SECTION = "flib"
LICENSE = "BSD & GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=3f16fa8e677e45af3127c5c4bafc3c00"

SRC_URI = "git://git.freescale.com/ppc/sdk/flib.git"
SRCREV = "64a3233ae426f36119268381c62100b02f7fe7e1"

S = "${WORKDIR}/git"

do_install(){
    oe_runmake install DESTDIR=${D}
}

ALLOW_EMPTY_${PN} = "1"
