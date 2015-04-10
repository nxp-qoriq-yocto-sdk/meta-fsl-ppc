DESCRIPTION = "A Linux-based utility supporting console multiplexing and demultiplexing"
SECTION = "mux-server"
LICENSE = "LGPL-2.1"
# TODO: add a dedicated COPYING file
LIC_FILES_CHKSUM = "file://mux_server.c;endline=9;md5=e59eeb0812bb88b7af2d932f2dc22aed"

SRC_URI = "file://mux-server-${PV}.tar.gz;name=mux_server"

EXTRA_OEMAKE='HOSTCC="${CC}"'

inherit deploy

do_install() {
    install -d ${D}${bindir}
    install -m 755 mux_server ${D}${bindir}
}

do_deploy() {
    install -d ${DEPLOYDIR}/hv
    install -m 755 mux_server ${DEPLOYDIR}/hv/
}
addtask deploy after do_install

BBCLASSEXTEND = "native nativesdk"

