require samba.inc
require samba-basic.inc
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://../COPYING;md5=d32239bcb673463ab874e80d47fae504"
PR = "r1"

SRC_URI += "file://samba-3.4.0-configure-workaround.patch;striplevel=2 \
           file://samba3.4.0-pre-reply-write.patch;striplevel=2 \
           file://samba3.4.0-recvfile.patch;striplevel=2 \
	   file://samba-3.4.0-compatible-with-python-2.7.patch;patchdir=../"

SRC_URI[md5sum] = "a7137736379daf9855814ae14c2c5e22"
SRC_URI[sha256sum] = "405198cbef88b81210bde0b0734ae964861e3c8d2b600b1c7b32bb6f5f0244e6"

EXTRA_OECONF += "samba_cv_CC_NEGATIVE_ENUM_VALUES=yes \
                samba_cv_HAVE_EXPLICIT_LARGEFILE_SUPPORT=yes \
                samba_cv_have_longlong=yes \
                samba_cv_SIZEOF_OFF_T=yes \
		enable_external_libtalloc=no \
"

CFLAGS += "-D_LARGEFILE64_SOURCE -D_FILE_OFFSET_BITS=64 -D_GNU_SOURCE"
LDFLAGS += "-lavahi-common"

S = "${WORKDIR}/${PN}-${PV}/source3"

do_configure() {
	oe_runconf
}

do_compile() {
	base_do_compile
}

do_install_append() {
	# following folders are empty, remove them to fix following warning
	# WARNING: For recipe samba, the following files/directories were installed but not shipped in any package:
	# WARNING:   /usr/lib/gpext
	# WARNING:   /usr/lib/idmap
	# WARNING:   /usr/lib/perfcount
	# WARNING:   /usr/lib/nss_info
	# WARNING:   /usr/lib/rpc
	# WARNING:   /usr/lib/pdb
	rm -rf ${D}/usr/lib/gpext
	rm -rf ${D}/usr/lib/idmap
	rm -rf ${D}/usr/lib/perfcount
	rm -rf ${D}/usr/lib/nss_info
	rm -rf ${D}/usr/lib/rpc
	rm -rf ${D}/usr/lib/pdb
}
