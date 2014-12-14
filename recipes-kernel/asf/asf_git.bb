DESCRIPTION = "Non-DPAA software Application Specific Fast-path"
SECTION = "asf"
LICENSE = "GPLv2 & GPLv2+ & BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=b5881ecf398da8a03a3f4c501e29d287"

SRC_URI = "git://git.freescale.com/ppc/sdk/asf.git;nobranch=1"
SRCREV = "16eb472d6b2b34c8b605a86c469611bc8ddec1c9"

DEPENDS="virtual/kernel"
RDEPENDS_${PN} += "ipsec-tools"

inherit module qoriq_build_64bit_kernel

S = "${WORKDIR}/git/asfmodule"

EXTRA_OEMAKE = "CROSS_COMPILE=${TARGET_PREFIX}"
export KERNEL_PATH = "${STAGING_KERNEL_DIR}"

do_install(){
	mkdir -p ${D}/usr/driver/asf
	cp -rf ${S}/bin/full ${D}/usr/driver/asf
	cp -rf ${S}/bin/min  ${D}/usr/driver/asf
	cp -rf ${S}/../scripts ${D}/usr/driver/asf/.
}

FILES_${PN} += "/usr/driver/asf"
INHIBIT_PACKAGE_STRIP = "1"
