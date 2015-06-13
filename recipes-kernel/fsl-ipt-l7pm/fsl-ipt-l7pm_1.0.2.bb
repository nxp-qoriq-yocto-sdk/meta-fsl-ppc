DESCRIPTION = "Kernel Module using the layer7 filter for the Pattern Matcher"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8a71d0475d08eee76d8b6d0c6dbec543"

DEPENDS="virtual/kernel"

inherit module

SRC_URI = "git://git.freescale.com/ppc/sdk/fsl-ipt-l7pm.git;branch=master"
SRCREV = "ffe17faa0ab0eae6e37dc263f629f25eacb02b16"

S = "${WORKDIR}/git"

do_install() {
    install -d ${D}/etc/l7-protocols
    install -m 644 layer7.ini ${D}/etc/l7-protocols
    install -m 644 layer7.regex ${D}/etc/l7-protocols
    oe_runmake ARCH=powerpc INSTALL_MOD_PATH=${D} DEPMOD=/bin/true KERNEL_SRC="${STAGING_KERNEL_DIR}" install_modules
}

