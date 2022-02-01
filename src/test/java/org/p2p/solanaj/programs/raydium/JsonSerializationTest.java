package org.p2p.solanaj.programs.raydium;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.p2p.solanaj.programs.raydium.dto.FarmListDto;
import org.p2p.solanaj.programs.raydium.dto.LpListDto;
import org.p2p.solanaj.programs.raydium.dto.TokenListDto;
import org.junit.Test;

import java.io.IOException;

public class JsonSerializationTest {

  @Test
  public void jsonSerializationTest() throws IOException {

    ObjectMapper mapper = new ObjectMapper();

    TokenListDto tokenListDTO =
        mapper.readValue(
            this.getClass().getResourceAsStream("/raydium/tokenList.json"), TokenListDto.class);
    FarmListDto farmListDto =
        mapper.readValue(
            this.getClass().getResourceAsStream("/raydium/farmList.json"), FarmListDto.class);
    LpListDto lpListDto =
        mapper.readValue(
            this.getClass().getResourceAsStream("/raydium/liquidityList.json"), LpListDto.class);
  }
}
