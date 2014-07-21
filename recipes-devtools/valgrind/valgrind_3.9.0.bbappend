do_install_append_qoriq-ppc() {
    # Create a symbolic link as the user expects to see a /usr/bin/valgrind
    # On 64-bit MACHINE's, ensure that the link created, points to the *64-bit*
    # valgrind. Assume that the presence of the patterns: "\-64b" in ${MACHINE}
    # and "64" in ${TARGET_ARCH}, indicate the 64-bit flavour and that their
    # absence indicates 32-bit flavour respectively. Now, when both 32-bit and
    # 64-bit flavours are available to choose from; it does happen that even
    # though ${MACHINE} is chosen to indicate the choice of the 32-bit case, the
    # 64-bit tools are built too. So, ensure that on 32-bit, the link points
    # to the 32-bit valgrind, and of-course, that on 64-bit, the link points
    # to the 64-bit valgrind.
    if (!(echo "${MACHINE}"     | grep --quiet "\-64b") && # 32-bit
        !(echo "${TARGET_ARCH}" | grep --quiet "64")) ||
         (echo "${MACHINE}"     | grep --quiet "\-64b"  && # 64-bit
          echo "${TARGET_ARCH}" | grep --quiet "64"); then
        cd ${D}/${bindir}
        ln -sf ${TARGET_ARCH}-valgrind valgrind
    fi
}
