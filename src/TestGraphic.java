import java.awt.Color;

public class TestGraphic {
    public static void main(String args[]){

        int width = 600, height = 600;
        StdDraw.setCanvasSize(width, height);  //default is 512 x 512

        //Set the drawing scale to be 1 pixel per coordinate
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);

        StdDraw.setPenColor(Color.decode("#FFFFFF"));
        StdDraw.filledCircle(306.0, 336.0, 61.61168720299745);
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(Color.decode("#000000"));
        StdDraw.circle(306.0, 336.0, 61.61168720299745);

        // StdDraw.setPenColor(Color.decode("#FFDB00"));
        // StdDraw.filledPolygon(new double[]{163.0, 163.0, 163.0, 163.0, 163.0, 163.0, 163.0, 166.0, 170.0, 174.0, 180.0, 185.0, 201.0, 222.0, 238.0, 253.0, 276.0, 292.0, 316.0, 329.0, 346.0, 357.0, 372.0, 379.0, 393.0, 399.0, 409.0, 415.0, 425.0, 431.0, 438.0, 439.0, 442.0, 442.0, 442.0, 442.0, 441.0, 438.0, 432.0, 427.0, 421.0, 416.0, 405.0, 397.0, 384.0, 364.0, 347.0, 334.0, 315.0, 302.0, 287.0, 279.0, 273.0, 262.0, 248.0, 241.0, 231.0, 219.0, 209.0, 195.0, 185.0, 180.0, 171.0, 166.0, 159.0, 149.0, 143.0, 137.0, 131.0, 129.0, 127.0, 126.0, 126.0, 126.0, 126.0, 126.0, 127.0, 130.0, 132.0, 136.0, 139.0, 141.0, 143.0, 145.0, 147.0, 149.0, 151.0, 152.0, 152.0, 152.0, 152.0}, new double[]{405.0, 405.0, 405.0, 405.0, 405.0, 405.0, 410.0, 417.0, 425.0, 428.0, 432.0, 434.0, 440.0, 449.0, 455.0, 460.0, 466.0, 470.0, 473.0, 474.0, 474.0, 474.0, 473.0, 469.0, 460.0, 452.0, 437.0, 423.0, 393.0, 370.0, 337.0, 317.0, 290.0, 272.0, 248.0, 233.0, 215.0, 203.0, 185.0, 174.0, 162.0, 154.0, 142.0, 134.0, 125.0, 113.0, 106.0, 102.0, 100.0, 99.0, 99.0, 99.0, 99.0, 101.0, 106.0, 109.0, 112.0, 117.0, 121.0, 133.0, 141.0, 146.0, 156.0, 163.0, 173.0, 189.0, 200.0, 217.0, 237.0, 247.0, 257.0, 279.0, 292.0, 301.0, 310.0, 327.0, 338.0, 351.0, 360.0, 368.0, 373.0, 376.0, 378.0, 383.0, 388.0, 394.0, 401.0, 403.0, 403.0, 403.0, 403.0});
        // StdDraw.setPenRadius(0.005);
        // StdDraw.setPenColor(Color.decode("#000000"));
        // StdDraw.polygon(new double[]{163.0, 163.0, 163.0, 163.0, 163.0, 163.0, 163.0, 166.0, 170.0, 174.0, 180.0, 185.0, 201.0, 222.0, 238.0, 253.0, 276.0, 292.0, 316.0, 329.0, 346.0, 357.0, 372.0, 379.0, 393.0, 399.0, 409.0, 415.0, 425.0, 431.0, 438.0, 439.0, 442.0, 442.0, 442.0, 442.0, 441.0, 438.0, 432.0, 427.0, 421.0, 416.0, 405.0, 397.0, 384.0, 364.0, 347.0, 334.0, 315.0, 302.0, 287.0, 279.0, 273.0, 262.0, 248.0, 241.0, 231.0, 219.0, 209.0, 195.0, 185.0, 180.0, 171.0, 166.0, 159.0, 149.0, 143.0, 137.0, 131.0, 129.0, 127.0, 126.0, 126.0, 126.0, 126.0, 126.0, 127.0, 130.0, 132.0, 136.0, 139.0, 141.0, 143.0, 145.0, 147.0, 149.0, 151.0, 152.0, 152.0, 152.0, 152.0}, new double[]{405.0, 405.0, 405.0, 405.0, 405.0, 405.0, 410.0, 417.0, 425.0, 428.0, 432.0, 434.0, 440.0, 449.0, 455.0, 460.0, 466.0, 470.0, 473.0, 474.0, 474.0, 474.0, 473.0, 469.0, 460.0, 452.0, 437.0, 423.0, 393.0, 370.0, 337.0, 317.0, 290.0, 272.0, 248.0, 233.0, 215.0, 203.0, 185.0, 174.0, 162.0, 154.0, 142.0, 134.0, 125.0, 113.0, 106.0, 102.0, 100.0, 99.0, 99.0, 99.0, 99.0, 101.0, 106.0, 109.0, 112.0, 117.0, 121.0, 133.0, 141.0, 146.0, 156.0, 163.0, 173.0, 189.0, 200.0, 217.0, 237.0, 247.0, 257.0, 279.0, 292.0, 301.0, 310.0, 327.0, 338.0, 351.0, 360.0, 368.0, 373.0, 376.0, 378.0, 383.0, 388.0, 394.0, 401.0, 403.0, 403.0, 403.0, 403.0});
        // StdDraw.setPenColor(Color.decode("#FFDB00"));
        // StdDraw.filledPolygon(new double[]{166.0, 166.0, 166.0, 166.0, 166.0, 166.0, 166.0, 166.0, 166.0, 167.0, 172.0, 177.0, 181.0, 188.0, 196.0, 202.0, 210.0, 216.0, 224.0, 230.0, 235.0, 242.0, 248.0, 252.0, 256.0, 258.0, 259.0, 260.0, 261.0, 262.0, 262.0, 263.0, 263.0, 263.0, 263.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 263.0, 263.0, 262.0, 261.0, 259.0, 257.0, 255.0, 253.0, 248.0, 243.0, 235.0, 225.0, 220.0, 215.0, 208.0, 204.0, 199.0, 195.0, 191.0, 188.0, 184.0, 182.0, 180.0, 179.0, 178.0, 178.0, 178.0, 177.0, 177.0, 176.0, 175.0, 174.0, 173.0, 172.0, 172.0, 172.0, 172.0, 172.0, 172.0}, new double[]{334.0, 334.0, 334.0, 334.0, 334.0, 334.0, 334.0, 334.0, 335.0, 336.0, 338.0, 341.0, 343.0, 345.0, 349.0, 350.0, 353.0, 354.0, 356.0, 358.0, 358.0, 358.0, 358.0, 358.0, 358.0, 358.0, 357.0, 356.0, 354.0, 351.0, 350.0, 348.0, 348.0, 348.0, 348.0, 348.0, 347.0, 347.0, 347.0, 347.0, 347.0, 347.0, 347.0, 347.0, 347.0, 347.0, 347.0, 347.0, 347.0, 347.0, 347.0, 347.0, 347.0, 347.0, 347.0, 346.0, 346.0, 345.0, 344.0, 343.0, 342.0, 341.0, 340.0, 339.0, 338.0, 335.0, 332.0, 331.0, 329.0, 327.0, 326.0, 324.0, 324.0, 324.0, 323.0, 323.0, 323.0, 323.0, 324.0, 324.0, 325.0, 326.0, 327.0, 327.0, 328.0, 328.0, 329.0, 329.0, 329.0, 329.0, 329.0, 330.0, 330.0, 330.0});
        // StdDraw.setPenRadius(0.005);
        // StdDraw.setPenColor(Color.decode("#000000"));
        // StdDraw.polygon(new double[]{166.0, 166.0, 166.0, 166.0, 166.0, 166.0, 166.0, 166.0, 166.0, 167.0, 172.0, 177.0, 181.0, 188.0, 196.0, 202.0, 210.0, 216.0, 224.0, 230.0, 235.0, 242.0, 248.0, 252.0, 256.0, 258.0, 259.0, 260.0, 261.0, 262.0, 262.0, 263.0, 263.0, 263.0, 263.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 264.0, 263.0, 263.0, 262.0, 261.0, 259.0, 257.0, 255.0, 253.0, 248.0, 243.0, 235.0, 225.0, 220.0, 215.0, 208.0, 204.0, 199.0, 195.0, 191.0, 188.0, 184.0, 182.0, 180.0, 179.0, 178.0, 178.0, 178.0, 177.0, 177.0, 176.0, 175.0, 174.0, 173.0, 172.0, 172.0, 172.0, 172.0, 172.0, 172.0}, new double[]{334.0, 334.0, 334.0, 334.0, 334.0, 334.0, 334.0, 334.0, 335.0, 336.0, 338.0, 341.0, 343.0, 345.0, 349.0, 350.0, 353.0, 354.0, 356.0, 358.0, 358.0, 358.0, 358.0, 358.0, 358.0, 358.0, 357.0, 356.0, 354.0, 351.0, 350.0, 348.0, 348.0, 348.0, 348.0, 348.0, 347.0, 347.0, 347.0, 347.0, 347.0, 347.0, 347.0, 347.0, 347.0, 347.0, 347.0, 347.0, 347.0, 347.0, 347.0, 347.0, 347.0, 347.0, 347.0, 346.0, 346.0, 345.0, 344.0, 343.0, 342.0, 341.0, 340.0, 339.0, 338.0, 335.0, 332.0, 331.0, 329.0, 327.0, 326.0, 324.0, 324.0, 324.0, 323.0, 323.0, 323.0, 323.0, 324.0, 324.0, 325.0, 326.0, 327.0, 327.0, 328.0, 328.0, 329.0, 329.0, 329.0, 329.0, 329.0, 330.0, 330.0, 330.0});
        // StdDraw.setPenColor(Color.decode("#FFDB00"));
        // StdDraw.filledPolygon(new double[]{305.0, 305.0, 305.0, 305.0, 305.0, 305.0, 305.0, 305.0, 305.0, 305.0, 305.0, 307.0, 309.0, 311.0, 313.0, 317.0, 322.0, 326.0, 332.0, 337.0, 343.0, 346.0, 353.0, 358.0, 365.0, 371.0, 377.0, 380.0, 383.0, 386.0, 390.0, 391.0, 393.0, 394.0, 395.0, 396.0, 396.0, 396.0, 396.0, 396.0, 397.0, 397.0, 399.0, 399.0, 400.0, 400.0, 400.0, 400.0, 400.0, 400.0, 400.0, 400.0, 399.0, 399.0, 398.0, 396.0, 395.0, 393.0, 389.0, 386.0, 382.0, 375.0, 370.0, 362.0, 357.0, 354.0, 344.0, 337.0, 331.0, 324.0, 318.0, 313.0, 309.0, 305.0, 304.0, 303.0, 302.0, 301.0, 300.0, 300.0, 300.0, 300.0, 300.0, 300.0, 300.0}, new double[]{344.0, 344.0, 344.0, 344.0, 344.0, 344.0, 344.0, 344.0, 344.0, 344.0, 345.0, 345.0, 345.0, 345.0, 345.0, 346.0, 348.0, 350.0, 353.0, 355.0, 357.0, 358.0, 360.0, 361.0, 362.0, 363.0, 363.0, 363.0, 363.0, 362.0, 360.0, 359.0, 357.0, 356.0, 355.0, 355.0, 354.0, 354.0, 354.0, 354.0, 354.0, 354.0, 353.0, 353.0, 353.0, 353.0, 353.0, 353.0, 353.0, 353.0, 353.0, 353.0, 353.0, 352.0, 352.0, 351.0, 350.0, 349.0, 348.0, 347.0, 345.0, 342.0, 340.0, 337.0, 336.0, 335.0, 332.0, 330.0, 329.0, 328.0, 327.0, 327.0, 328.0, 330.0, 331.0, 333.0, 335.0, 338.0, 338.0, 339.0, 339.0, 339.0, 339.0, 339.0, 339.0});
        // StdDraw.setPenRadius(0.005);
        // StdDraw.setPenColor(Color.decode("#000000"));
        // StdDraw.polygon(new double[]{305.0, 305.0, 305.0, 305.0, 305.0, 305.0, 305.0, 305.0, 305.0, 305.0, 305.0, 307.0, 309.0, 311.0, 313.0, 317.0, 322.0, 326.0, 332.0, 337.0, 343.0, 346.0, 353.0, 358.0, 365.0, 371.0, 377.0, 380.0, 383.0, 386.0, 390.0, 391.0, 393.0, 394.0, 395.0, 396.0, 396.0, 396.0, 396.0, 396.0, 397.0, 397.0, 399.0, 399.0, 400.0, 400.0, 400.0, 400.0, 400.0, 400.0, 400.0, 400.0, 399.0, 399.0, 398.0, 396.0, 395.0, 393.0, 389.0, 386.0, 382.0, 375.0, 370.0, 362.0, 357.0, 354.0, 344.0, 337.0, 331.0, 324.0, 318.0, 313.0, 309.0, 305.0, 304.0, 303.0, 302.0, 301.0, 300.0, 300.0, 300.0, 300.0, 300.0, 300.0, 300.0}, new double[]{344.0, 344.0, 344.0, 344.0, 344.0, 344.0, 344.0, 344.0, 344.0, 344.0, 345.0, 345.0, 345.0, 345.0, 345.0, 346.0, 348.0, 350.0, 353.0, 355.0, 357.0, 358.0, 360.0, 361.0, 362.0, 363.0, 363.0, 363.0, 363.0, 362.0, 360.0, 359.0, 357.0, 356.0, 355.0, 355.0, 354.0, 354.0, 354.0, 354.0, 354.0, 354.0, 353.0, 353.0, 353.0, 353.0, 353.0, 353.0, 353.0, 353.0, 353.0, 353.0, 353.0, 352.0, 352.0, 351.0, 350.0, 349.0, 348.0, 347.0, 345.0, 342.0, 340.0, 337.0, 336.0, 335.0, 332.0, 330.0, 329.0, 328.0, 327.0, 327.0, 328.0, 330.0, 331.0, 333.0, 335.0, 338.0, 338.0, 339.0, 339.0, 339.0, 339.0, 339.0, 339.0});
        // StdDraw.setPenColor(Color.decode("#FFDB00"));
        // StdDraw.filledPolygon(new double[]{280.0, 280.0, 280.0, 280.0, 280.0, 280.0, 280.0, 280.0, 280.0, 281.0, 285.0, 290.0, 295.0, 298.0, 301.0, 303.0, 305.0, 306.0, 306.0, 306.0, 306.0, 306.0, 306.0, 306.0, 306.0, 306.0, 306.0, 306.0, 306.0, 306.0, 305.0, 302.0, 300.0, 297.0, 294.0, 285.0, 275.0, 263.0, 257.0, 251.0, 249.0, 248.0, 248.0, 247.0, 247.0, 247.0, 247.0, 247.0, 247.0, 248.0, 248.0, 249.0, 249.0, 249.0}, new double[]{303.0, 303.0, 303.0, 303.0, 303.0, 303.0, 303.0, 303.0, 302.0, 299.0, 293.0, 283.0, 276.0, 271.0, 265.0, 262.0, 260.0, 258.0, 258.0, 258.0, 258.0, 258.0, 258.0, 258.0, 258.0, 258.0, 258.0, 258.0, 258.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 258.0});
        // StdDraw.setPenRadius(0.005);
        // StdDraw.setPenColor(Color.decode("#000000"));
        // StdDraw.polygon(new double[]{280.0, 280.0, 280.0, 280.0, 280.0, 280.0, 280.0, 280.0, 280.0, 281.0, 285.0, 290.0, 295.0, 298.0, 301.0, 303.0, 305.0, 306.0, 306.0, 306.0, 306.0, 306.0, 306.0, 306.0, 306.0, 306.0, 306.0, 306.0, 306.0, 306.0, 305.0, 302.0, 300.0, 297.0, 294.0, 285.0, 275.0, 263.0, 257.0, 251.0, 249.0, 248.0, 248.0, 247.0, 247.0, 247.0, 247.0, 247.0, 247.0, 248.0, 248.0, 249.0, 249.0, 249.0}, new double[]{303.0, 303.0, 303.0, 303.0, 303.0, 303.0, 303.0, 303.0, 302.0, 299.0, 293.0, 283.0, 276.0, 271.0, 265.0, 262.0, 260.0, 258.0, 258.0, 258.0, 258.0, 258.0, 258.0, 258.0, 258.0, 258.0, 258.0, 258.0, 258.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 257.0, 258.0});
        // StdDraw.setPenColor(Color.decode("#FFDB00"));
        // StdDraw.filledPolygon(new double[]{189.0, 189.0, 189.0, 189.0, 189.0, 189.0, 189.0, 189.0, 197.0, 206.0, 224.0, 236.0, 257.0, 274.0, 294.0, 308.0, 328.0, 340.0, 354.0, 364.0, 376.0, 381.0, 386.0, 387.0, 389.0, 389.0, 389.0, 389.0, 389.0, 389.0, 389.0, 389.0, 389.0, 389.0, 389.0, 389.0, 389.0, 389.0, 389.0, 389.0, 389.0, 389.0, 388.0, 387.0, 385.0, 382.0, 376.0, 371.0, 363.0, 356.0, 343.0, 333.0, 317.0, 305.0, 291.0, 276.0, 260.0, 247.0, 226.0, 214.0, 198.0, 184.0, 175.0, 172.0, 171.0, 171.0, 171.0}, new double[]{218.0, 218.0, 218.0, 218.0, 218.0, 218.0, 218.0, 218.0, 218.0, 218.0, 218.0, 218.0, 218.0, 218.0, 218.0, 218.0, 218.0, 218.0, 219.0, 219.0, 220.0, 221.0, 222.0, 222.0, 223.0, 223.0, 223.0, 223.0, 223.0, 223.0, 223.0, 223.0, 223.0, 223.0, 222.0, 222.0, 221.0, 220.0, 218.0, 217.0, 215.0, 213.0, 211.0, 209.0, 207.0, 204.0, 200.0, 197.0, 193.0, 190.0, 186.0, 184.0, 181.0, 178.0, 175.0, 171.0, 169.0, 169.0, 169.0, 169.0, 175.0, 185.0, 193.0, 195.0, 197.0, 198.0, 198.0});
        // StdDraw.setPenRadius(0.005);
        // StdDraw.setPenColor(Color.decode("#000000"));
        // StdDraw.polygon(new double[]{189.0, 189.0, 189.0, 189.0, 189.0, 189.0, 189.0, 189.0, 197.0, 206.0, 224.0, 236.0, 257.0, 274.0, 294.0, 308.0, 328.0, 340.0, 354.0, 364.0, 376.0, 381.0, 386.0, 387.0, 389.0, 389.0, 389.0, 389.0, 389.0, 389.0, 389.0, 389.0, 389.0, 389.0, 389.0, 389.0, 389.0, 389.0, 389.0, 389.0, 389.0, 389.0, 388.0, 387.0, 385.0, 382.0, 376.0, 371.0, 363.0, 356.0, 343.0, 333.0, 317.0, 305.0, 291.0, 276.0, 260.0, 247.0, 226.0, 214.0, 198.0, 184.0, 175.0, 172.0, 171.0, 171.0, 171.0}, new double[]{218.0, 218.0, 218.0, 218.0, 218.0, 218.0, 218.0, 218.0, 218.0, 218.0, 218.0, 218.0, 218.0, 218.0, 218.0, 218.0, 218.0, 218.0, 219.0, 219.0, 220.0, 221.0, 222.0, 222.0, 223.0, 223.0, 223.0, 223.0, 223.0, 223.0, 223.0, 223.0, 223.0, 223.0, 222.0, 222.0, 221.0, 220.0, 218.0, 217.0, 215.0, 213.0, 211.0, 209.0, 207.0, 204.0, 200.0, 197.0, 193.0, 190.0, 186.0, 184.0, 181.0, 178.0, 175.0, 171.0, 169.0, 169.0, 169.0, 169.0, 175.0, 185.0, 193.0, 195.0, 197.0, 198.0, 198.0});

    }
}
