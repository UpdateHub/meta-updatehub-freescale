FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

UPDATEHUB_IMX_PATCHES = "\
    file://fw_env.config \
    file://updatehub.cfg \
"

SRC_URI:append:updatehub-imx = " ${UPDATEHUB_IMX_PATCHES}"
