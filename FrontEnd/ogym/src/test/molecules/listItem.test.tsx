import { prettyDOM, render } from "@testing-library/react";
import Button from "../../components/atoms/Button";
import ListItem from "../../components/molecules/ListItem";


test('ListItem test', () => {
  const { getAllByText } = render(
       <ListItem>
        <Button text='button' backgroundColor="blue"></Button>
        <Button text='button' backgroundColor="blue"></Button>
      </ListItem>
  )
  const header = getAllByText('button');
  console.log(header.length);
  expect(header.length).toBe(2);
});