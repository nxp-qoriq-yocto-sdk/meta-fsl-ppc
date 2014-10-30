DESCRIPTION = "UIO driver for T1040 L2 Switch"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

SRC_URI = "git://git.freescale.com/ppc/sdk/l2switch-uio.git"
SRCREV = "00c8040f2439ac58cf8274b1c411b14256e295c9"

inherit module

S = "${WORKDIR}/git/uio-driver"

COMPATIBLE_MACHINE = "(t1040qds|t1040rdb|t1040rdb-64b|t1042rdb|t1042rdb-64b|t1024rdb|t1024qds)"
