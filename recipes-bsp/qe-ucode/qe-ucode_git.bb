DESCRIPTION = "qe microcode binary"
SECTION = "qe-ucode"
LICENSE = "Freescale-EULA"
LIC_FILES_CHKSUM = "file://EULA;md5=60037ccba533a5995e8d1a838d85799c"

inherit deploy

SRC_URI = "git://git.freescale.com/ppc/sdk/qe-ucode.git;nobranch=1"
SRCREV= "49efc94b553de5c2a9bd28093592eff0068e161c"

S = "${WORKDIR}/git"

QE_UCODE ?= "fsl_qe_ucode_1021_10_A.bin"
QE_UCODE_t1040 = "iram_Type_A_T1040_r1.0.bin"
QE_UCODE_t1042 = "iram_Type_A_T1040_r1.0.bin"
QE_UCODE_t1024 = "iram_Type_A_T1024_r1.0.bin"

do_install () {
	install -d ${D}/
	install -m 644 ${QE_UCODE} ${D}/
}

do_deploy () {
	install -d ${DEPLOYDIR}/
	install -m 644 ${QE_UCODE} ${DEPLOYDIR}/
}
addtask deploy before do_build after do_install

PACKAGES += "${PN}-image"
FILES_${PN}-image += "/*"
ALLOW_EMPTY_${PN} = "1"
COMPATIBLE_MACHINE = "(p1021rdb|p1025twr|t1040|t1042|t1024)"

