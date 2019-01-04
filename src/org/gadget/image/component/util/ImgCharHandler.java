package org.gadget.image.component.util;

import org.gadget.image.component.CharMatrixData;
import org.gadget.image.component.ImgMatrixData;

/**
 * 字符图像处理器
 */
public class ImgCharHandler {

//    private int[][] GrayScaleMatrix;
    private final static String index = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. ";
    private final static double ratio = index.length() / 256;

    /**
     * 灰度矩阵 转换为 字符矩阵
     *
     * @param img 图像信息
     * @return 字符矩阵
     */
    private char[][] convGSM(ImgMatrixData img) {
        // 字符矩阵
        char[][] cm = new char[img.height][img.width];
        // 灰度映射字符
        for (int cmy = 0; cmy < img.height; cmy++) {
            for (int cmx = 0; cmx < img.width; cmx++) {
                cm[cmy][cmx] = img.alpha[cmy][cmx] == 0
                        ? ' ' : index.charAt((int) (ratio * img.gray[cmy][cmx]));
            }
        }
        return cm;
    }

    /**
     * 图像数据转换为字符矩阵
     *
     * @param img 图像信息
     * @return 字符信息
     * @throws Exception 图像信息异常；或精度指定异常
     */
    public CharMatrixData toChatMatrix(ImgMatrixData img) throws Exception {
        // Gray Scale matrix
        img.gray = PixelHandler.getGSMatrix(img);
        // chat matrix
        CharMatrixData data = new CharMatrixData(img.width, img.height);
        // put data
        data.matrix = convGSM(img);
        return data;
    }
}
