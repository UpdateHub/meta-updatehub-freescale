FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

UPDATEHUB_IMX_PATCHES = " \
    file://0001-UpdateHub-Add-common-header.patch \
"

SRC_URI_append_updatehub-imx = " ${UPDATEHUB_IMX_PATCHES}"
