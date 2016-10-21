package ru.mironenko.chesstask;

/**
 * Created by nikita on 19.10.2016.
 */
public interface Figure {

        String getColor();

        /**
         * A method that determines can figure move like this
         * @param finalX the final x location
         * @param finalY the final y location
         * @return a boolean indicating whether the path is valid
         */
        boolean isValidPath(int startX, int startY, int finalX, int finalY);


}
