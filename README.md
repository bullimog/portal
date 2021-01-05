# portal


Temperature Correction calculation is from https://straighttothepint.com/hydrometer-temperature-correction/

CG = corrected gravity
MG = measured gravity
TR = temperature (fahrenheit) at time of reading
TC = calibration temperature of hydrometer

CG = MG * ((1.00130346 – 0.000134722124 * TR + 0.00000204052596 * TR – 0.00000000232820948 * TR) / (1.00130346 – 0.000134722124 * TC + 0.00000204052596 * TC – 0.00000000232820948 * TC))
