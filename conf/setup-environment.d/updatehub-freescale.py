def __after_init_updatehub_freescale():
    PLATFORM_ROOT_DIR = os.environ['PLATFORM_ROOT_DIR']

    append_layers([ os.path.join(PLATFORM_ROOT_DIR, 'sources', p) for p in
                    [
                        'meta-fsl-arm',
                        'meta-fsl-arm-extra',
                        'meta-updatehub-freescale',
                    ]])
    # FSL EULA
    eulas.accept['meta-fsl-arm/EULA'] = 'ACCEPT_FSL_EULA = "1"'

run_after_init(__after_init_updatehub_freescale)
