import { useEffect, useState } from "react";
import { Container, Typography } from "@mui/material";
import { fetchTop5PowerPlants, PowerPlant } from "../../api/powerPlants";
import { PowerPlantTable } from "../../components/PowerPlantTable";

const Home = () => {
  const [powerPlants, setPowerPlants] = useState<PowerPlant[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      const data = await fetchTop5PowerPlants();
      setPowerPlants(data);
      setLoading(false);
    };
    fetchData();
  }, []);

  return (
    <Container>
      <Typography variant="h4" gutterBottom>
        Top 5 Largest Power Plants
      </Typography>
      <PowerPlantTable powerPlants={powerPlants} loading={loading} />
    </Container>
  );
};

export default Home;
