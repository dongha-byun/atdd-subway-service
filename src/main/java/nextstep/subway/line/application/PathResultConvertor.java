package nextstep.subway.line.application;

import java.util.List;
import java.util.stream.Collectors;
import nextstep.subway.auth.domain.LoginMember;
import nextstep.subway.line.domain.Charge;
import nextstep.subway.line.domain.PathResult;
import nextstep.subway.line.dto.PathResponse;
import nextstep.subway.station.dto.StationResponse;

public class PathResultConvertor {
    public static PathResponse convert(PathResult pathResult) {

        List<StationResponse> stations = pathResult.getStations().stream()
                .map(StationResponse::of)
                .collect(Collectors.toList());

        Charge charge = new Charge(pathResult.getDistance(), pathResult.getLines());

        return new PathResponse(stations, pathResult.getDistance(), charge.value());
    }

    public static PathResponse convert(PathResult pathResult, LoginMember loginMember) {

        List<StationResponse> stations = pathResult.getStations().stream()
                .map(StationResponse::of)
                .collect(Collectors.toList());

        Charge charge = new Charge(pathResult.getDistance(), pathResult.getLines(), loginMember.getAge());

        return new PathResponse(stations, pathResult.getDistance(), charge.value());
    }
}
