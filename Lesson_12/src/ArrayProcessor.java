public class ArrayProcessor {
    public int processArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4) {
            throw new MyArraySizeException("Неправильный размер массива: должно быть 4 строки");
        }
        for (String[] row : array) {
            if (row.length != 4) {
                throw new MyArraySizeException("Неправильный размер массива: должно быть 4 столбца в каждой строке");
            }
        }

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Ошибка преобразования данных в ячейке [" + i + "][" + j + "]");
                }
            }
        }
        return sum;
    }
}
