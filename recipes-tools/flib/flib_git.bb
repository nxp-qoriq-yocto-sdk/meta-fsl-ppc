DESCRIPTION = "Foundation Library"
SECTION = "flib"
LICENSE = "BSD & GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=3f16fa8e677e45af3127c5c4bafc3c00"

SRC_URI = "git://git.freescale.com/ppc/sdk/flib.git"
SRCREV = "1f7e467917db1306154ad143e68dab20c1354e0d"

S = "${WORKDIR}/git"

do_install(){
    oe_runmake install DESTDIR=${D}
}
