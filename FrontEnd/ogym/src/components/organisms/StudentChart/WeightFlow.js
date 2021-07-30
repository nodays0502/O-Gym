import React from "react";
import { ResponsiveLine } from "@nivo/line";
// make sure parent container have a defined height when using
// responsive component, otherwise height will be 0 and
// no chart will be rendered.
// website examples showcase many properties,
// you'll often use just a few of them.

const data = [
  {
    id: "목표 체중",
    color: "hsl(121, 70%, 50%)",
    data: [
      {
        x: "1월",
        y: 67,
      },
      {
        x: "2월",
        y: 67,
      },
      {
        x: "3월",
        y: 67,
      },
      {
        x: "4월",
        y: 67,
      },
      {
        x: "5월",
        y: 67,
      },
      {
        x: "6월",
        y: 67,
      },
      {
        x: "7월",
        y: 67,
      },
      {
        x: "8월",
        y: 67,
      },
      {
        x: "9월",
        y: 67,
      },
      {
        x: "10월",
        y: 67,
      },
      {
        x: "11월",
        y: 67,
      },
      {
        x: "12월",
        y: 67,
      },
    ],
  },
  {
    id: "나의 체중",
    color: "hsl(142, 70%, 50%)",
    data: [
      {
        x: "1월",
        y: 82,
      },
      {
        x: "2월",
        y: 79,
      },
      {
        x: "3월",
        y: 76,
      },
      {
        x: "4월",
        y: 78,
      },
      {
        x: "5월",
        y: 74,
      },
      {
        x: "6월",
        y: 71,
      },
      {
        x: "7월",
        y: 69,
      },
    ],
  },
];

function WeightFlow() {
  return (
    <ResponsiveLine
      data={data}
      margin={{ top: 50, right: 110, bottom: 50, left: 60 }}
      xScale={{ type: "point" }}
      yScale={{
        type: "linear",
        min: "auto",
        max: "auto",
        stacked: false,
        reverse: false,
      }}
      yFormat=" >-.2f"
      axisTop={null}
      axisRight={null}
      axisBottom={{
        orient: "bottom",
        tickSize: 5,
        tickPadding: 5,
        tickRotation: 0,
        legend: "체중 변화",
        legendOffset: 36,
        legendPosition: "middle",
      }}
      axisLeft={{
        orient: "left",
        tickSize: 5,
        tickPadding: 5,
        tickRotation: 0,
        legend: "체중",
        legendOffset: -40,
        legendPosition: "middle",
      }}
      lineWidth={6}
      enablePoints={false}
      pointSize={10}
      pointColor={{ theme: "background" }}
      pointBorderWidth={2}
      pointBorderColor={{ from: "serieColor" }}
      pointLabelYOffset={-12}
      useMesh={true}
      legends={[
        {
          anchor: "bottom-right",
          direction: "column",
          justify: false,
          translateX: 100,
          translateY: 0,
          itemsSpacing: 0,
          itemDirection: "left-to-right",
          itemWidth: 80,
          itemHeight: 20,
          itemOpacity: 0.75,
          symbolSize: 12,
          symbolShape: "circle",
          symbolBorderColor: "rgba(0, 0, 0, .5)",
          effects: [
            {
              on: "hover",
              style: {
                itemBackground: "rgba(0, 0, 0, .03)",
                itemOpacity: 1,
              },
            },
          ],
        },
      ]}
    />
  );
}

export default WeightFlow;
