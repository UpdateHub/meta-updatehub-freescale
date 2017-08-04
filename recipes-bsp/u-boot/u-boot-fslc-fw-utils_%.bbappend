FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_updatehub-imx  = " file://fw_env.config"
