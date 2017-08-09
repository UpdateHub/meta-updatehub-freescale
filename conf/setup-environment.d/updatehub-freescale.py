def __after_init_updatehub_freescale():
    PLATFORM_ROOT_DIR = os.environ['PLATFORM_ROOT_DIR']

    append_layers([ os.path.join(PLATFORM_ROOT_DIR, 'sources', p) for p in
                    [
                        'meta-fsl-arm',
                        'meta-fsl-arm-extra',
                        'meta-updatehub-freescale',
                    ]])

run_after_init(__after_init_updatehub_freescale)
