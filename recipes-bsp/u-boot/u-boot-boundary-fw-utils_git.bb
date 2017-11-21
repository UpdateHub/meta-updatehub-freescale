SUMMARY = "U-Boot bootloader fw_printenv/setenv utilities"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

DEPENDS = "mtd-utils"

PV = "v2016.03+git${SRCPV}"

SRCREV = "0dbffd30b6a81b53c8a4780490ea625ccbfa5596"
SRCBRANCH = "boundary-v2016.03"
SRC_URI = " \
    git://github.com/boundarydevices/u-boot-imx6.git;branch=${SRCBRANCH} \
    file://default-gcc.patch \
    file://fw_env.config \
"

S = "${WORKDIR}/git"
INSANE_SKIP_${PN} = "already-stripped"
EXTRA_OEMAKE_class-target = 'CROSS_COMPILE=${TARGET_PREFIX} CC="${CC} ${CFLAGS} ${LDFLAGS}" HOSTCC="${BUILD_CC} ${BUILD_CFLAGS} ${BUILD_LDFLAGS}" V=1'
EXTRA_OEMAKE_class-cross = 'ARCH=${TARGET_ARCH} CC="${CC} ${CFLAGS} ${LDFLAGS}" V=1'

inherit uboot-config

do_compile () {
	oe_runmake ${UBOOT_MACHINE}
	oe_runmake env
}

do_install () {
    install -Dm 0755 ${S}/tools/env/fw_printenv ${D}${base_sbindir}/fw_printenv
    install -Dm 0755 ${S}/tools/env/fw_printenv ${D}${base_sbindir}/fw_setenv
    install -Dm 0644 ${WORKDIR}/fw_env.config   ${D}${sysconfdir}/fw_env.config
}

do_install_class-cross () {
	install -d ${D}${bindir_cross}
	install -m 755 ${S}/tools/env/fw_printenv ${D}${bindir_cross}/fw_printenv
	install -m 755 ${S}/tools/env/fw_printenv ${D}${bindir_cross}/fw_setenv
}

SYSROOT_DIRS_append_class-cross = " ${bindir_cross}"

RPROVIDES_${PN} += "u-boot-fw-utils"

PACKAGE_ARCH = "${MACHINE_ARCH}"
BBCLASSEXTEND = "cross"
