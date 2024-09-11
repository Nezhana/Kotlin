/*
	Багаторядковий коментар
	Функція main -
	точка входу у програму
*/
fun main() {	// початок функції main
    val name = "Kotlin"
    val greetings = "Hello, $name!!!"	// val => immutable variable
    println(greetings)

    var age = 20	// var => mutable variable
    println(age)
    age = 21
    println(age)

    var PI = 3.14f
    println(PI)

    println("Age is: $age, and PI is: $PI")

    var something: Any = "Holala"	// Any => dynamic type
    println(something)
    something = 654321
    println(something)

    val a = 10
    val b = 26

    val c = if (a > b) {
        println( "a = $a" )
        a
    } else {
        println( "b = $b" )
        b
    }
    println(c) // 26

    when (val d = a + b) {
        10 -> println( "a + b = 10" )
        20 -> println( "a + b = 20" )
        else -> println( "d = $d" ) // 36
    }

    for (n in 1..9) {
        print( "${n * n} \t" )
    }

    var i = 10
    while (i > 0) { // first action
        println(i*i) // next action
        i--;
    }

    var j = -1
    do {
        println(j*j) //first action
        j--;
    }
    while (j > 0) // next action

    val range1 = 1..5 // 1 2 3 4 5
    val range2 = 5 downTo 1 // 5 4 3 2 1
    val range3 = 1..10 step 2 // 1 3 5 7 9
    val range4 = 1 until 9 // 1 2 3 4 5 6 7 8

    val numbers: Array<Int> = arrayOf(1, 2, 3, 4, 5)
    /* val numbers = Array(3, {5}) // [5, 5, 5] */
    /* val numbers = Array(3, {i++ * 2}) // [2, 4, 6] */
    val num = numbers[1] // отримуємо другий елемент n=2
    numbers[2] = 7 // записуємо у третій елемент число 7
    println( "numbers[2] = ${numbers[2]}" ) // numbers[2] = 7
    println(numbers)
    for (number in numbers){
        print( "$number " )
    }
    println()
    println(num)
    /*
    var i = 0
    while ( i in numbers.indices) {
        println(numbers[i])
        i++;
    }
     */
}