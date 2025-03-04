import {  TableBody, TableCell, TableRow } from "@mui/material";
import { PowerPlantTableProps, StyledTable, StyledTableContainer, StyledTableHead, StyledTableRow } from "./styles";



export const PowerPlantTable = ({ powerPlants }: PowerPlantTableProps) => {
  return (
    <StyledTableContainer>
      <StyledTable>
        <StyledTableHead>
          <TableRow>
            <TableCell>Name</TableCell>
            <TableCell>State</TableCell>
            <TableCell>Capacity (kW)</TableCell>
            <TableCell>Type</TableCell>
            <TableCell>Status</TableCell>
          </TableRow>
        </StyledTableHead>
        <TableBody>
          {powerPlants.map((plant) => (
            <StyledTableRow key={plant.id}>
              <TableCell>{plant.name}</TableCell>
              <TableCell>{plant.state}</TableCell>
              <TableCell>{plant.capacity.toLocaleString()}</TableCell>
              <TableCell>{plant.generationType}</TableCell>
              <TableCell>{plant.status}</TableCell>
            </StyledTableRow>
          ))}
        </TableBody>
      </StyledTable>
    </StyledTableContainer>
  );
};

