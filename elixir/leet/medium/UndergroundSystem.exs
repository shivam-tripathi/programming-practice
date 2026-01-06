defmodule UndergroundSystem do
  @type t() :: %__MODULE__{}

  defstruct user_state: %{},
            station_meta: %{}

  @spec init_() :: UndergroundSystem
  def init_() do
    %UndergroundSystem{}
  end

  @spec check_in(
          ug :: UndergroundSystem,
          id :: integer,
          station_name :: String.t(),
          t :: integer
        ) :: UndergroundSystem
  def check_in(ug, id, station_name, t) do
    %UndergroundSystem{
      ug
      | user_state: Map.put(ug.user_state, id, {station_name, t})
    }
  end

  @spec check_out(
          ug :: UndergroundSystem.t(),
          id :: integer,
          station_name :: String.t(),
          t :: integer
        ) :: any
  def check_out(ug, id, station_name, t) do
    end_station = station_name
    check_out_time = t
    {start_station, check_in_time} = ug.user_state[id]

    end_station_meta = Map.get(ug.station_meta, end_station, %{})
    {total_travels, total_time} = Map.get(end_station_meta, start_station, {0, 0})

    end_station_meta =
      Map.put(
        end_station_meta,
        start_station,
        {total_travels + 1, total_time + check_out_time - check_in_time}
      )

    %UndergroundSystem{ug | station_meta: Map.put(ug.station_meta, end_station, end_station_meta)}
  end

  @spec get_average_time(
          ug :: UndergroundSystem,
          start_station :: String.t(),
          end_station :: String.t()
        ) :: float
  def get_average_time(ug, start_station, end_station) do
    {total_travels, total_time} = ug.station_meta[end_station][start_station]
    total_time / total_travels
  end
end

defmodule SolutionTest do
  def test([], _data, ug) do
    ug
  end

  def test([head | tail], [data | data_tail], ug) do
    # IO.inspect(%{head: head, data: data})

    cond do
      head == "UndergroundSystem" ->
        test(tail, data_tail, UndergroundSystem.init_())

      head == "checkIn" ->
        [id, station, time] = data
        ug = UndergroundSystem.check_in(ug, id, station, time)
        test(tail, data_tail, ug)

      head == "checkOut" ->
        [id, station, time] = data
        ug = UndergroundSystem.check_out(ug, id, station, time)
        test(tail, data_tail, ug)

      head == "getAverageTime" ->
        [start_station, end_station] = data
        IO.puts(UndergroundSystem.get_average_time(ug, start_station, end_station))
        test(tail, data_tail, ug)
    end
  end
end

SolutionTest.test(
  [
    "UndergroundSystem",
    "checkIn",
    "checkOut",
    "getAverageTime",
    "checkIn",
    "checkOut",
    "getAverageTime",
    "checkIn",
    "checkOut",
    "getAverageTime"
  ],
  [
    [],
    [10, "Leyton", 3],
    [10, "Paradise", 8],
    ["Leyton", "Paradise"],
    [5, "Leyton", 10],
    [5, "Paradise", 16],
    ["Leyton", "Paradise"],
    [2, "Leyton", 21],
    [2, "Paradise", 30],
    ["Leyton", "Paradise"]
  ],
  nil
)

# Your functions will be called as such:
ug = UndergroundSystem.init_()
ug = UndergroundSystem.check_in(ug, 23, "station_name_A", 100)
ug = UndergroundSystem.check_out(ug, 23, "station_name_B", 200)
IO.puts(UndergroundSystem.get_average_time(ug, "station_name_A", "station_name_B"))
