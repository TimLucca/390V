defmodule E do 
    defp pow(x, n) when n == 0, do: 1
    defp pow(x, n) when n > 0, do: x * pow(x, n - 1)
    defp fac(num) when num > 1, do: num * fac(num - 1)
    defp fac(num) when num == 1, do: 1
    defp calc(x, k) when k == 0, do: 1
    defp calc(x, k) when k > 0, do: pow(x, k) / fac(k) + calc(x, k - 1)
    def main(args \\ []) do 
        {x, _} = IO.gets("Enter x: ") |> Integer.parse
        {k, _} = IO.gets("Enter accuracy: ") |> Integer.parse 
        IO.puts("e^#{x} = #{calc(x, k)}")
    end 
end