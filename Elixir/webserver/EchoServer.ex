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
        [req, page, prot] = String.split(line, " ")
        file = get_file(String.replace_prefix(page, "/", ""))
        send_header(socket, prot)
        file_data(file)
    end

    defp get_file(page) do
        cond do 
            page == "" -> "index.html"
            File.exists?(page) -> page 
            true -> "404.html"
        end 
    end 

    defp send_header(socket, prot) do
        {{year, mon, day}, _} = :calendar.local_time()
        :gen_tcp.send(socket, "#{prot} 200 OK\n")
        :gen_tcp.send(socket, "Date: #{mon}/#{day}/#{year}\n")
        :gen_tcp.send(socket, "Server: Elixier Server : 0.01\n")
        :gen_tcp.send(socket, "Content-type: text/html\n\n")
    end

    defp file_data(name) do
        {:ok, file} = File.open(name)
        data = IO.binread(file, :all)
        :ok = File.close(file)
        data
    end 


    def main(args \\ []) do 
        accept(9999)
    end 
end

