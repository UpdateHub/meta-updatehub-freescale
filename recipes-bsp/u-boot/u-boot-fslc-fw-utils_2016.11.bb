SUMMARY = "U-Boot bootloader fw_printenv/setenv utilities"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

DEPENDS = "mtd-utils"

SRCREV = "c44711d91e32a5738875fe505efc54f6958fd64e"
SRCBRANCH = "2016.11+fslc"
SRC_URI = "git://github.com/Freescale/u-boot-fslc.git;branch=${SRCBRANCH}"

S = "${WORKDIR}/git"

INSANE_SKIP_${PN} = "already-stripped"
EXTRA_OEMAKE_class-target = 'CROSS_COMPILE=${TARGET_PREFIX} CC="${CC} ${CFLAGS} ${LDFLAGS}" V=1'
EXTRA_OEMAKE_class-cross = 'ARCH=${TARGET_ARCH} CC="${CC} ${CFLAGS} ${LDFLAGS}" V=1'

inherit uboot-config

do_compile () {
    oe_runmake ${UBOOT_MACHINE}
    oe_runmake env
}

do_install () {
    install -d ${D}${base_sbindir}
    install -d ${D}${sysconfdir}
    install -m 755 ${S}/tools/env/fw_printenv ${D}${base_sbindir}/fw_printenv
    install -m 755 ${S}/tools/env/fw_printenv ${D}${base_sbindir}/fw_setenv

    if [ -e ${WORKDIR}/fw_env.config ]; then
        install -m 0644 ${WORKDIR}/fw_env.config ${D}${sysconfdir}/fw_env.config
    else
        install -m 0644 ${S}/tools/env/fw_env.config ${D}${sysconfdir}/fw_env.config
    fi
}

do_install_class-cross () {
    install -d ${D}${bindir_cross}
    install -m 755 ${S}/tools/env/fw_printenv ${D}${bindir_cross}/fw_printenv
    install -m 755 ${S}/tools/env/fw_printenv ${D}${bindir_cross}/fw_setenv
}

SYSROOT_PREPROCESS_FUNCS_class-cross = "uboot_fw_utils_cross"
uboot_fw_utils_cross() {
    sysroot_stage_dir ${D}${bindir_cross} ${SYSROOT_DESTDIR}${bindir_cross}
}

RPROVIDES_${PN} += "u-boot-fw-utils"

PACKAGE_ARCH = "${MACHINE_ARCH}"
BBCLASSEXTEND = "cross"
