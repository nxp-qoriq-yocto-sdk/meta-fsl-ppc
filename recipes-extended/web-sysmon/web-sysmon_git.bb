DESCRIPTION = "Web System Monitor Files"
SECTION = "web-sysmon"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

SRC_URI = "git://git.freescale.com/ppc/sdk/web-sysmon-dev.git;nobranch=1"
SRCREV = "8d0c6eca1113832fabe917fd0cb25abe2d4d7157"

inherit update-rc.d

S = "${WORKDIR}/git"

RDEPENDS_${PN} = " \
    coreutils \
    cronie \
    bc \
    lighttpd \
    lighttpd-module-cgi \
    lmsensors-sensors \
    cairo \
    rrdtool \
    liberation-fonts \
    make \
"

EXTRA_OEMAKE += "D=${D}"
do_install () {
        oe_runmake install
}

FILES_${PN} += "/"

INITSCRIPT_NAME = "web-sysmon.sh"
INITSCRIPT_PARAMS = "defaults 99 20"
