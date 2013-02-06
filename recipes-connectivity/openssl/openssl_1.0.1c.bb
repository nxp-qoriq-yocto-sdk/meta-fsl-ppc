require openssl.inc

# For target side versions of openssl enable support for OCF Linux driver
# if they are available.
CFLAG += "-DHAVE_CRYPTODEV -DUSE_CRYPTODEV_DIGESTS"

PR = "${INC_PR}.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=f9a8f968107345e0b75aa8c2ecaa7ec8"

export DIRS = "crypto ssl apps engines"
export OE_LDFLAGS="${LDFLAGS}"

SRC_URI += "file://configure-targets.patch \
            file://shared-libs.patch \
            file://oe-ldflags.patch \
            file://engines-install-in-libdir-ssl.patch \
            file://openssl-fix-link.patch \
            file://debian/ca.patch \
            file://debian/config-hurd.patch \
            file://debian/debian-targets.patch \
            file://debian/make-targets.patch \
            file://debian/man-dir.patch \
            file://debian/man-section.patch \
            file://debian/no-rpath.patch \
            file://debian/no-symbolic.patch \
            file://debian/pic.patch \
            file://debian/valgrind.patch \
            file://debian/rehash-crt.patch \
            file://debian/rehash_pod.patch \
            file://debian/shared-lib-ext.patch \
            file://debian/stddef.patch \
            file://debian/version-script.patch \
            file://debian/gnu_source.patch \
            file://debian/c_rehash-compat.patch \
            file://debian/libdoc-manpgs-pod-spell.patch \
            file://debian/libssl-misspell.patch \
            file://debian/pod_req_misspell2.patch \
            file://debian/pod_pksc12.misspell.patch \
            file://debian/pod_s_server.misspell.patch \
            file://debian/pod_x509setflags.misspell.patch \
            file://debian/pod_ec.misspell.patch \
            file://debian/pkcs12-doc.patch \
            file://debian/dgst_hmac.patch \
            file://debian/block_diginotar.patch \
            file://debian/block_digicert_malaysia.patch \
            file://debian/c_rehash-multi.patch \
            file://debian/renegiotate_tls.patch \
            file://debian/default_bits.patch \
            file://openssl_fix_for_x32.patch \
            file://find.pl \
           "

SRC_URI[md5sum] = "ae412727c8c15b67880aef7bd2999b2e"
SRC_URI[sha256sum] = "2a9eb3cd4e8b114eb9179c0d3884d61658e7d8e8bf4984798a5f5bd48e325ebe"

PACKAGES =+ " \
	${PN}-engines \
	${PN}-engines-dbg \
	"

FILES_${PN}-engines = "${libdir}/ssl/engines/*.so ${libdir}/engines"
FILES_${PN}-engines-dbg = "${libdir}/ssl/engines/.debug"

PARALLEL_MAKEINST = ""

do_configure_prepend() {
  cp ${WORKDIR}/find.pl ${S}/util/find.pl
}
