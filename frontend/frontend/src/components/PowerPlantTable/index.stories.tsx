import { Meta, StoryObj } from "@storybook/react";
import { PowerPlant } from "../../api/powerPlants";
import { PowerPlantTable } from ".";

export default {
  title: "Components/PowerPlantTable",
  component: PowerPlantTable,
} as Meta<typeof PowerPlantTable>;

const mockData: PowerPlant[] = [
  {
    id: 1,
    codeCEG: "UTE.GN.RJ.038173-0.1",
    name: "GNA II",
    state: "RJ",
    energySource: "Fóssil",
    generationType: "UTE",
    capacity: 1672599,
    status: "Em andamento",
    connectionType: "Subestação",
    connectionCompany: "NEOENERGIA GUANABARA TRANSMISSAO DE ENERGIA S.A.",
    connectionVoltage: 500,
    reportDate: "2025-02-20",
  },
  {
    id: 2,
    codeCEG: "UTE.GN.CE.037748-1.1",
    name: "Portocém I",
    state: "CE",
    energySource: "Fóssil",
    generationType: "UTE",
    capacity: 1571888,
    status: "Não Iniciada",
    connectionType: "Subestação",
    connectionCompany: "Cia. Hidro Elétrica do São Francisco",
    connectionVoltage: 500,
    reportDate: "2024-03-01",
  },
  {
    id: 3,
    codeCEG: "UTE.GN.PA.037748-1.2",
    name: "Portocém I",
    state: "PA",
    energySource: "Fóssil",
    generationType: "UTE",
    capacity: 1571888,
    status: "Em andamento",
    connectionType: "Subestação",
    connectionCompany: "CENTRAIS ELETRICAS DO NORTE DO BRASIL S/A ELETRONORTE",
    connectionVoltage: 500,
    reportDate: "2025-02-20",
  },
  {
    id: 4,
    codeCEG: "UTN.UR.RJ.030150-7.1",
    name: "Angra III",
    state: "RJ",
    energySource: "Nuclear",
    generationType: "UTN",
    capacity: 1350000,
    status: "Paralisada",
    connectionType: "Subestação",
    connectionCompany: "FURNAS - CENTRAIS ELETRICAS S.A.",
    connectionVoltage: 500,
    reportDate: "2025-02-20",
  },
  {
    id: 5,
    codeCEG: "UTE.GN.RJ.032955-0.2",
    name: "GNA I",
    state: "RJ",
    energySource: "Fóssil",
    generationType: "UTE",
    capacity: 1338300,
    status: "Em andamento",
    connectionType: "Subestação",
    connectionCompany: "FURNAS-Centrais Elétricas S/A",
    connectionVoltage: 345,
    reportDate: "2021-09-15",
  },
];

export const Default: StoryObj<typeof PowerPlantTable> = {
  args: { powerPlants: mockData, loading: false },
};

export const Loading: StoryObj<typeof PowerPlantTable> = {
  args: { powerPlants: [], loading: true },
};
