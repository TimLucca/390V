defmodule E do 
    def pow(x, n, acc \\ 1)
    def pow(x, n, acc) when n == 0, do: acc
    def pow(x, n, acc) when n > 0, do: pow(x, n - 1, acc * x)
    def fac(num, acc \\ 1)
    def fac(num, acc) when num > 1, do: fac(num - 1, acc * num)
    def fac(num, acc) when num <= 1, do: acc
    def calc(x, k, acc \\ 1)
    def calc(x, k, acc) when k == 0, do: acc
    def calc(x, k, acc) when k > 0, do: calc(x, k-1, pow(x, k) / fac(k) + acc)
    def main(args \\ []) do 
        {x, _} = IO.gets("Enter x: ") |> Integer.parse
        {k, _} = IO.gets("Enter accuracy: ") |> Integer.parse 
        IO.puts("e^#{x} = #{calc(x, k)}")
    end 
end