# meta-soms-in-space

A Yocto layer for deploying the "Soms In Space" game on PHYTEC hardware.

## Description

This layer provides recipes and configuration to install the Soms In Space game, a PHYTEC demo project where you play as a PHYTEC SoM making your way to outer space. It configures the game to run automatically as a systemd service and includes display configuration options.

Soms In Space Source: https://github.com/phytec-labs/SomsInSpace

## Dependencies

* poky (zeus or later)
* meta-phytec (for PHYTEC hardware support)
  * meta-ampliphy

## Usage

1. Add this layer to your Yocto build
2. Configure display options in your local.conf (optional)
3. Build and install the image with soms in space demo service included

```
bitbake phytec-somsinspace-image
```

Example local.conf configuration:

## Configuration

The layer provides the following display configuration options to be added to local.conf:

* `DISPLAY_TYPE`: Configure display output
  * `lvds`: Use LVDS display (default)
  * `hdmi`: Use HDMI display
  * `none`: Don't configure display

* `DISPLAY_ROTATION`: Configure screen rotation
  * `rotate-90`: Rotate 90 degrees (default)
  * `normal` or `none`: No rotation
  * `rotate-180`: Rotate 180 degrees
  * `rotate-270`: Rotate 270 degrees

### local.conf examples

``` ini
# Default settings. This creates a portrait display using LVDS.
# If nothing is added to the local.conf, this is what is used
DISPLAY_TYPE = "lvds"
DISPLAY_ROTATION = "rotate-90"

# Switch to HDMI output
DISPLAY_TYPE = "hdmi"
# Disable rotation
DISPLAY_ROTATION = "normal"
```

## License

This layer is provided under the MIT license.
