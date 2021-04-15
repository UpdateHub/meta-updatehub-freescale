FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

UPDATEHUB_IMX_PATCHES = "\
    file://fw_env.config \
    file://updatehub.cfg \
"

SRC_URI_append_updatehub-imx = " ${UPDATEHUB_IMX_PATCHES}"
