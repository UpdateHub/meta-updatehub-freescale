def __after_init_updatehub_freescale():
    PLATFORM_ROOT_DIR = os.environ['PLATFORM_ROOT_DIR']

    append_layers([ os.path.join(PLATFORM_ROOT_DIR, 'sources', p) for p in
                    [
                        'meta-freescale',
                        'meta-freescale-3rdparty',
                        'meta-updatehub-freescale',
                    ]])
    # FSL EULA
    eulas.accept['meta-freescale/EULA'] = 'ACCEPT_FSL_EULA = "1"'

run_after_init(__after_init_updatehub_freescale)
