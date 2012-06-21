DESCRIPTION = "Reset Control Words (RCW)"
SECTION = "rcw"
LICENSE = "BSD"
PR = "r4"

LIC_FILES_CHKSUM = " \
	file://p2041rdb/LICENSE;md5=96dd72f26e9bb861de5c76c60e35e1bc \
	file://p3041ds/LICENSE;md5=96dd72f26e9bb861de5c76c60e35e1bc \
	file://p4080ds/LICENSE;md5=96dd72f26e9bb861de5c76c60e35e1bc \
	file://p5020ds/LICENSE;md5=96dd72f26e9bb861de5c76c60e35e1bc \
"

# this package is specific to the machine itself
INHIBIT_DEFAULT_DEPS = "1"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy

SRCREV = "394db85c496f6e41dd7e29e54dab3db2b380b187"
SRC_URI = "git://git.freescale.com/ppc/sdk/rcw.git"

S = "${WORKDIR}/git"

do_install () {
	make install

	M=`echo ${MACHINE} | sed s/-64b//g`
	install -d ${D}/boot/rcw
	cp -r ${S}/${M}/${M}/* ${D}/boot/rcw
}

do_deploy () {
	M=`echo ${MACHINE} | sed s/-64b//g`
	install -d ${DEPLOYDIR}/rcw
	cp -r ${S}/${M}/${M}/* ${DEPLOYDIR}/rcw
}
addtask deploy after do_install

PACKAGES += "${PN}-image"
FILES_${PN}-image += "/boot"

ALLOW_EMPTY_${PN} = "1"
