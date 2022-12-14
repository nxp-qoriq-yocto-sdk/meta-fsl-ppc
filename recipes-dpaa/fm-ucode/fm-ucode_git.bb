DESCRIPTION = "Fman microcode binary"
SECTION = "fm-ucode"
LICENSE = "Freescale-EULA"
LIC_FILES_CHKSUM = "file://EULA;md5=60037ccba533a5995e8d1a838d85799c"

PR = "r1"

inherit deploy

SRC_URI = "git://git.freescale.com/ppc/sdk/fm-ucode.git;nobranch=1"
SRCREV = "2827f0054e125b634fb1701719ca31c7d068e335"

S = "${WORKDIR}/git"

do_install () {
    case ${MACHINE} in
        t1024qds|t1024qds-64b|t1024rdb|t1024rdb-64b|t1023rdb|t1023rdb-64b) UCODE=t1024;;
        t1040d4rdb|t1040d4rdb-64b|t1042d4rdb|t1042d4rdb-64b|t1040qds|t1040qds-64b|t1040rdb|t1040rdb-64b|t1042rdb|t1042rdb-64b|t1042rdb-pi|t1042rdb-pi-64b) UCODE=t1040;;
        t2080qds|t2080qds-64b|t2080rdb|t2080rdb-64b) UCODE=t2080;;
        b4420qds|b4420qds-64b|b4860qds|b4860qds-64b) UCODE=b4860;;
        t4240qds|t4240qds-64b|t4240rdb|t4240rdb-64b|t4160qds|t4160qds-64b) UCODE=t4240;;
        p5020ds|p5020ds-64b) UCODE=p5020;;
        p5040ds|p5040ds-64b) UCODE=p5040;;
        *) UCODE=${MACHINE};;
    esac
    UCODE=`echo $UCODE | sed -e 's,[a-zA-Z]*$,,'`
    install -d ${D}/boot
    install -m 644 fsl_fman_ucode_${UCODE}*.bin ${D}/boot/
}

do_deploy () {
    case ${MACHINE} in
        t1024qds|t1024qds-64b|t1024rdb|t1024rdb-64b|t1023rdb|t1023rdb-64b) UCODE=t1024;;
        t1040d4rdb|t1040d4rdb-64b|t1042d4rdb|t1042d4rdb-64b|t1040qds|t1040qds-64b|t1040rdb|t1040rdb-64b|t1042rdb|t1042rdb-64b|t1042rdb-pi|t1042rdb-pi-64b) UCODE=t1040;;
        t2080qds|t2080qds-64b|t2080rdb|t2080rdb-64b) UCODE=t2080;;
        b4420qds|b4420qds-64b|b4860qds|b4860qds-64b) UCODE=b4860;;
        t4240qds|t4240qds-64b|t4240rdb|t4240rdb-64b|t4160qds|t4160qds-64b) UCODE=t4240;;
        p5020ds|p5020ds-64b) UCODE=p5020;;
        p5040ds|p5040ds-64b) UCODE=p5040;;
        *) UCODE=${MACHINE};;
    esac
    UCODE=`echo $UCODE | sed -e 's,[a-zA-Z]*$,,'`
    install -d ${DEPLOYDIR}/
    install -m 644 fsl_fman_ucode_${UCODE}*.bin ${DEPLOYDIR}/
}
addtask deploy before do_build after do_install

PACKAGES += "${PN}-image"
FILES_${PN}-image += "/boot"
COMPATIBLE_MACHINE = "(p1023rdb|e500mc|e5500|e5500-64b|e6500|e6500-64b)"

