import ru.zenit.icap.ICAP
import ru.zenit.icap.ICAPException
import java.io.IOException

fun main(args: Array<String>) {
    println("An example of using the ICAP scanner.")

    if (args.isEmpty()) {
        println("Submit a list of files to check")
    } else {
        check(args)
    }
}

fun check(files: Array<String>) {
    try {
        val icap = ICAP("127.0.0.1", 1344, "avscan") // 172.20.75.144

        for (file in files) {
            try {
                print("$file: ")
                val result: Boolean = icap.scanFile(file)
                println(if (result) "Clean" else "Infected")
            } catch (ex: ICAPException) {
                System.err.println("Could not scan file " + file + ": " + ex.message)
            } catch (ex: IOException) {
                System.err.println("IO error occurred when scanning file " + file + ": " + ex.message)
            }
        }
    } catch (ex: IOException) {
        println(ex.message)
    } catch (ex: ICAPException) {
        println(ex.message)
    }
}