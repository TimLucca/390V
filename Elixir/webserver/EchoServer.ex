defmodule EchoServer do
    require Logger 

    def accept(port) do
        {:ok, socket} = :gen_tcp.listen(port, [:binary, packet: :line, active: false, reuseaddr: true])
        Logger.info "Accepting connections on port: #{port}"
        loop_acceptor(socket)
    end 

    defp loop_acceptor(socket) do 
        {:ok, client} = :gen_tcp.accept(socket)
        Logger.info "Connected"
        Task.start_link(fn -> serve(client) end)
        loop_acceptor(socket)
    end 
    
    defp serve(socket) do  
        socket |> read_line() |> write_line(socket)
        :ok = :gen_tcp.close(socket)
    end 

    defp read_line(socket) do 
        {:ok, data} = :gen_tcp.recv(socket, 0)
        data
    end 

    defp write_line(line, socket) do 
        IO.puts(line)
        :gen_tcp.send(socket, proc_req(socket, line)) 
    end 

    defp proc_req(socket, line) do 
        [req, file, prot] = String.split(line, " ")
        send_header(socket, prot)
        file_data(file)
    end

    defp send_header(socket, prot) do
        :gen_tcp.send(socket, "HTTP/1.1 200 OK\n")
        :gen_tcp.send(socket, "Date: 4/16/2019\n")
        :gen_tcp.send(socket, "Server: Elixier Server : 0.01\n")
        :gen_tcp.send(socket, "Content-type: text/html\n")
        :gen_tcp.send(socket, "Content-length: \n\n")
        IO.puts("sent header")
    end

    defp file_data(name \\ "index.html") do
        {:ok, file} = File.open("index.html")
        data = IO.binread(file, :all)
        :ok = File.close(file)
        data
    end 


    def main(args \\ []) do 
        accept(9999)
    end 
end

