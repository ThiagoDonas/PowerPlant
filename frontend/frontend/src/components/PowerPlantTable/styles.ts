import styled from "styled-components";
import { PowerPlant } from "../../api/powerPlants";
import { Table, TableContainer, TableHead, TableRow } from "@mui/material";

export interface PowerPlantTableProps {
  powerPlants: PowerPlant[];
  loading: boolean;
}


export const StyledTableContainer = styled(TableContainer)`
  margin: 40px auto;
  width: 80%;
  background-color: #ffff;
  border-radius: 10px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.5);
  overflow: hidden;
`;

export const StyledTable = styled(Table)`
  min-width: 650px;
`;

export const StyledTableHead = styled(TableHead)`
  background-color: #0D47A1;

  & th {
    color: white;
    font-weight: bold;
    font-size: 16px;
    text-transform: uppercase;
  }
`;

export const StyledTableRow = styled(TableRow)`
  &:nth-of-type(odd) {
    background-color: rgba(255, 255, 255, 0.05);
  }
`;

