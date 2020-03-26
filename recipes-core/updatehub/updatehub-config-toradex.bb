SUMMARY = "updatehub configuration for Toradex machines"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
INHIBIT_DEFAULT_DEPS = "1"

SRC_URI = "file://unlock_emmc"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -Dm 0755 ${WORKDIR}/unlock_emmc ${D}${datadir}/updatehub/state-change-callbacks.d/unlock_emmc
}

FILES_${PN} += "${datadir}/updatehub/state-change-callbacks.d/unlock_emmc"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(apalis-imx6|colibri-imx6|colibri-imx7-emmc)"
