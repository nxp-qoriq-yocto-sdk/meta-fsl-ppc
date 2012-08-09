LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

PV = "3.0.34"
PR = "r4"

SRCREV = "774388f7a49e11773e9dfa125a71350d6ae04377"
SRC_URI = "git://git.freescale.com/ppc/sdk/linux.git \
	  "

KSRC ?= ""
S = '${@base_conditional("KSRC", "", "${WORKDIR}/git", "${KSRC}", d)}'

# make everything compatible for the time being
COMPATIBLE_MACHINE_$MACHINE = "$MACHINE"
