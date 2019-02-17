case class Charge(cc: CreditCard, amount: Double) {
    def combine(other: Charge) : Charge =
        if (cc == other.cc)
            Charge(cc, amount + other.amout)
        else
            throw new Exception("Can't combine charges of different cards")
}

class Cafe {
    def buyCoffee(cc: CreditCard) : (Coffee, Charge) = {
        val cup = new Coffee()
            (cup, Charge(cc, cup.price))
    }
    def buyCoffees(cc: CreditCard, n: Int): (List[Coffee], Charge) = {
        val purchases: List[(Coffee, Charge)] = List.fill(n)(buyCoffee(cc))
        val (coffee, charges) = purchases.unzip
        (coffees, charges.reduce((c1, c2) =< c1.combine(c2)))
    }
}

def coalesce(carges: List[Charge]): List[Charge] = 
    charges.groupBy(_.cc).values.map(_.reduce(_combine_)).toList

