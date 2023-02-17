fun main() {

    lesson9_lambda_expressions()

}

fun lesson1_functions_and_their_parameters(){
    // Local function
    fun displayUser(name: String, age : Int = 18, position : String = "unemployed"){
        println("Name: $name    Age: $age   Position: $position")
    }

    displayUser("Tom", position = "Manager", age = 23)
    displayUser("Alice", 21)
    displayUser("Kate")
}

fun lesson2_variable_number_of_parameters(){

    // Local functions
    fun varargTest(vararg string : String){
        for(str in string)
            print("$str ")
    }
    fun varargSum(vararg numbers: Int) : Int{
        var result = 0
        for(n in numbers)
            result += n
        return result
    }
    fun spreadOperator(vararg numbers : Int, koef : Int){
        for(number in numbers)
            print("${number * koef} ")
    }

    varargTest("Tom", "Bob", "And I")
    println()
    varargTest("Tom", "Bob", "Max", "Nick", "Michele")

    println()
    println()

    println(varargSum(1, 2, 3, 4, 5))
    println(varargSum(1, 2, 3, 4, 5, 6, 7, 8, 9))

    println()
    println()

    val num = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
    spreadOperator(*num, koef = 2)

}

fun lesson3_returning_the_result(){

}

fun lesson4_single_line_and_local_funcitons(){

    fun ageIsValid(age : Int) = age in 1..110
    fun compareAge(age1: Int, age2: Int){
        if(!ageIsValid(age1) || !ageIsValid(age2)){
            println("Age is invalid")
            return
        }

        when{
            age1 == age2 -> println("age1 == age2")
            age1 > age2  -> println("age1 > age2")
            age1 < age2  -> println("age1 < age2")
        }
    }

    compareAge(20, 23)
    compareAge(-3, 20)
    compareAge(34, 134)
    compareAge(15, 8)

}

fun lesson5_function_overload(){
    fun sum(a : Int, b: Int) = a + b
    fun sum(a: Double, b: Double) = a + b
    fun sum(a: Int, b: Int, c: Int) = a + b + c
    fun sum(a: Int, b: Double) = a + b
    fun sum(a: Double, b: Int) = a + b

    val a = sum(1, 2)
    val b = sum(1.5, 2.5)
    val c = sum(1, 2, 3)
    val d = sum(2, 1.5)
    val e = sum(1.5, 2)

}

fun lesson6_type_of_function(){

    fun hello() = println("hello")
    val message: () -> Unit
    message = :: hello
    message()


    fun sum(a: Int, b: Int) = a + b
    fun subtract(a: Int, b: Int) = a - b
    var operator: (Int, Int) -> Int

    operator = ::sum
    println(operator(1, 2))

    operator = ::subtract
    println(operator(5, 1))

}

fun lesson7_high_order_function(){

    fun displayMessage(mes: () -> Unit){
        mes()
    }
    fun morning() = println("Good Morning")
    fun evening() = println("Good Evening")

    displayMessage(::morning)
    displayMessage { evening() }


    fun action (n1: Int, n2: Int, op: (Int, Int) -> Int){
        println(op(n1, n2))
    }
    fun sum(a:Int, b:Int) = a + b
    fun subtract(a: Int, b: Int) = a - b
    fun multiply(a: Int, b: Int) = a * b

    action(4, 2, ::sum)
    action(4, 2, ::subtract)
    action(4, 2, ::multiply)


    println()
    fun empty(a: Int, b: Int) = 0
    fun selectAction(key: Int): (Int, Int) -> Int{
        return when(key){
            1 -> ::sum
            2 -> ::subtract
            3 -> ::multiply
            else -> ::empty

        }
    }

    val act1 = selectAction(1)
    println(act1(2,4))

    val act2 = selectAction(2)
    println(act2(4,2))

}

fun lesson8_anonymous_funcitons(){
    val message = fun(x: Int, y: Int) = x + y

    fun doOperation(a: Int, b: Int, op: (Int, Int) -> Int){
        println(op(a, b))
    }
    doOperation(5, 2, fun(x: Int, y: Int) = x + y)
    doOperation(5, 2, fun(x: Int, y: Int) = x - y)

    val op = fun(x: Int, y: Int) = x * y
    doOperation(5, 2, op)


    println()


    fun selectAction(key: Int): (Int, Int) -> Int{
        return when(key){
            1 -> fun(x: Int, y: Int) = x + y
            2 -> fun(x: Int, y: Int) = x - y
            3 -> fun(x: Int, y: Int) = x * y
            else -> fun(x: Int, y: Int) = 0
        }
    }

    val act1 = selectAction(1)
    println(act1(5,2))

    val act2 = selectAction(2)
    println(act2(5,2))

    val act3 = selectAction(5)
    println(act3(5,2))
}

fun lesson9_lambda_expressions(){
    fun part1() {
        val printer = { message: String -> println(message) }
        printer("hello world")
        printer("Good Bye")

        println()
        println()

        val sum = { x: Int, y: Int -> println(x + y) }
        sum(1, 2)
        sum(4, 3)

        println()
        println()

        val ans = { x: Int, y: Int ->
            val res = x + y
            println("$x + $y = $res")
            res
        }

        ans(1, 2)
    }

    fun doOperation(x: Int, y: Int, op: (Int, Int) -> Int){
        val res = op(x, y)
        println(res)
    }
    fun selectFun(key: Int): (Int, Int) -> Int{
        return when(key){
            1 -> {x, y -> x + y}
            2 -> {x, y -> x * y}
            3 -> {x, y -> x - y}
            else -> { _, _ -> 0}
        }
    }
    fun part2(){
        val sum = {x: Int, y: Int -> x + y}
        doOperation(5, 3, sum)

        doOperation(4, 6, {x: Int, y: Int -> x * y})
        // trailing lambda
        doOperation(4, 6) {x: Int, y: Int -> x * y}

        println()
        println()

        val act1 = selectFun(1)
        println(act1(3, 4))

        val act2 = selectFun(2)
        println(act2(3, 4))

        val act3 = selectFun(3)
        println(act3(3, 4))
    }



    part1()
    part2()
}