DESCRIPTION = "Hypervisor Config"
SECTION = "hv-cfg"
LICENSE = "BSD"
PR = "r6"

LIC_FILES_CHKSUM = " \
	file://p2041rdb/LICENSE;md5=96dd72f26e9bb861de5c76c60e35e1bc \
	file://p3041ds/LICENSE;md5=96dd72f26e9bb861de5c76c60e35e1bc \
	file://p4080ds/LICENSE;md5=96dd72f26e9bb861de5c76c60e35e1bc \
	file://p5020ds/LICENSE;md5=96dd72f26e9bb861de5c76c60e35e1bc \
"

DEPENDS += "dtc-native"

# this package is specific to the machine itself
INHIBIT_DEFAULT_DEPS = "1"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy

SRC_URI = "git://git.freescale.com/ppc/sdk/hv-cfg.git;nobranch=1"
SRCREV = "f79080739851b3a3dfcd435f2ef1572459a4313c"

S = "${WORKDIR}/git"

do_install () {
	make install

	M=`echo ${MACHINE} | sed s/-64b//g`
	if [ "t1040d4rdb" = "${M}" ] || [ "t1042rdb" = "${M}" ] || [ "t1042d4rdb" = "${M}" ];then
		M=t1040rdb
	fi
	install -d ${D}/boot/hv-cfg
	cp -r ${S}/${M}/${M}/* ${D}/boot/hv-cfg
}

do_deploy () {
	M=`echo ${MACHINE} | sed s/-64b//g`
	if [ "t1040d4rdb" = "${M}" ] || [ "t1042rdb" = "${M}" ] || [ "t1042d4rdb" = "${M}" ];then
		M=t1040rdb
	fi
	install -d ${DEPLOYDIR}/hv-cfg
	cp -r ${S}/${M}/${M}/* ${DEPLOYDIR}/hv-cfg
}
addtask deploy after do_install

PACKAGES += "${PN}-image"
FILES_${PN}-image += "/boot"

COMPATIBLE_HOST_qoriq-ppc = ".*"
COMPATIBLE_HOST ?= "(none)"
ALLOW_EMPTY_${PN} = "1"
