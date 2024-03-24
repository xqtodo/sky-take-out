package orderService;

import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;

import java.time.LocalDate;

/**
 * @Author: lxq
 * @Date: 2024/3/24 23:45
 */
public interface ReportService {
    TurnoverReportVO getTurnoverStatic(LocalDate begin, LocalDate end);

    UserReportVO getUserStatistics(LocalDate begin, LocalDate end);
}
