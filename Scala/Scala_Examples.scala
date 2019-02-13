object QuickSortInt {
    def qsort(list: List[Int]): List[Int] = list match {
        case Nil => Nil
        case pivot :: tail =>
            val (smaller, larger) = tail.partition(_ < pivot)
                qsort(smaller) ::: pivot :: qsort(larger)
    }

    def main(args: Array[String]){
        println(qsort(List(4,6,2,8,9,12,5,42)))
    }
}

