class EliudsEggs {

    int eggCount(int number) {
        int count = 0;

        while (number > 0) {
            if (number % 2 == 1) {
                count++;
            }
            number /= 2;
        }

        return count;
    }
}