LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

PV = "3.0.48"
PR = "r5"

SRCREV = "dd5a5aac3e15b16a4bccbab29c736bda0e7d55e7"
SRC_URI = "git://git.freescale.com/ppc/sdk/linux.git \
		file://0001-compiler.h-Undef-before-redefining-__attribute_const.patch \
	  "

KSRC ?= ""
S = '${@base_conditional("KSRC", "", "${WORKDIR}/git", "${KSRC}", d)}'

# make everything compatible for the time being
COMPATIBLE_MACHINE_$MACHINE = "$MACHINE"
