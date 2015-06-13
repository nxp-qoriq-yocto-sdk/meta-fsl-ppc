DESCRIPTION = "Reset Control Words (RCW)"
SECTION = "rcw"
LICENSE = "BSD"
PR = "r8"

LIC_FILES_CHKSUM = "file://rcw.py;beginline=8;endline=28;md5=9ba0b28922dd187b06b6c8ebcfdd208e"

# this package is specific to the machine itself
INHIBIT_DEFAULT_DEPS = "1"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy

SRC_URI = "git://git.freescale.com/ppc/sdk/rcw.git;nobranch=1"
SRCREV = "426f7a6535d93dac76f5125035e0938a85e778d2"

S = "${WORKDIR}/git"

export PYTHON

do_install () {
    make install

    M=`echo ${MACHINE} | sed s/-64b//g`
    if [ "t1042rdb-pi" = "${M}" ];then
        M=t1042rdb_pi
    fi
    install -d ${D}/boot/rcw
    cp -r ${S}/${M}/${M}/* ${D}/boot/rcw
}

do_deploy () {
    M=`echo ${MACHINE} | sed s/-64b//g`
    if [ "t1042rdb-pi" = "${M}" ];then
        M=t1042rdb_pi
    fi
    install -d ${DEPLOYDIR}/rcw
    cp -r ${S}/${M}/${M}/* ${DEPLOYDIR}/rcw
}
addtask deploy after do_install

PACKAGES += "${PN}-image"
FILES_${PN}-image += "/boot"

COMPATIBLE_HOST_qoriq-ppc = ".*"
COMPATIBLE_HOST ?= "(none)"
